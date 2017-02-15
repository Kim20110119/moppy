package excute.ad_areas;

import static common.constant.CommonConstants.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import common.Point;
import common.enquete.Manga_enquete;
import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * モッピー：漫画アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Ad_Enquete extends Point {
	/** 「daily-points」 */
	private static final String C_D_P = "daily-points";
	/** 「漫画アンケートURL」 */
	String enquete_link;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;
	/** 「アンケート件数」 */
	int enquete_count = 0;
	/** 「開始Index」 */
	int start = 0;
	/** 「終了Index」 */
	int end = 12;
	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();
	/**
	 * コンストラクタ
	 */
	public Moppy_Ad_Enquete() {
	}

	/**
	 * =================================================================================================================
	 * 漫画アンケート
	 * =================================================================================================================
	 *
	 * @return int point_couont 獲得済みポイント
	 *
	 * @author kimC
	 *
	 */
	public Integer execute(AccountBean pBean, Boolean loginFlag) {
		try {
			this.bean = pBean;
			if(loginFlag){
				// モッピー：ログイン画面
				driver.get(PC_LOGIN_URL);
				// モッピー：ログインメールアドレス
				sendkeysByStr(getByName(V_MAIL), bean.getMail());
				// モッピー：ログインパスワード
				sendkeysByStr(getByName(V_PASS), bean.getPassword());
				// モッピー：ログインボタン
				click(getByXpath(T_BUTTON, A_TYPE, V_SUBMIT));
			}
			// 「CMくじ」
			driver.get(PC_CM_URL);
			// 「漫画アンケートURL」取得する
			enquete_link = driver.findElement(By.className(C_D_P)).findElements(By.tagName(T_A)).get(INT_5)
					.getAttribute(A_HREF);
			if (StringUtils.isNoneEmpty(enquete_link)) {
				// 「漫画アンケート画面」
				driver.get(enquete_link);
				enquete_count = driver.findElements(By.partialLinkText("回答する")).size();
				if(enquete_count < end){
					end = enquete_count;
				}
				for(int i = start; i < end; i++){
					String enquete_url = driver.findElements(By.partialLinkText("回答する")).get(0).getAttribute(A_HREF);
					driver.get(enquete_url);
					start();
					// 「漫画アンケート画面」
					driver.get(enquete_link);
				}
				// 獲得ポイントカウント
				point_count += 10;
			} else {
				System.out.println("【エラー】：漫画アンケートURL取得失敗");
			}
			driver.quit();
			return point_count;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：漫画アンケート失敗");
			return point_count;
		}
	}

	/**
	 * =================================================================================================================
	 * 漫画アンケートスタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void start() {
		try {
			Manga_enquete.execute(driver, bean);
		} catch (Exception e) {
			System.out.println("【エラー】：漫画アンケート失敗！");
			point_count -= 10;
			restart();
		}
	}

	/**
	 * =================================================================================================================
	 * 漫画アンケート再スタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void restart() {
		try {
			Manga_enquete.execute(driver, bean);
		} catch (Exception e) {
			System.out.println("【エラー】：漫画アンケート再スタート失敗！");
		}
	}

}

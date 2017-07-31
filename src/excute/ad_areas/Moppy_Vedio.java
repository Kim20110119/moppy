package excute.ad_areas;

import static common.Common.netCheck;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

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
public class Moppy_Vedio extends Point {
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
	public Moppy_Vedio() {
		// モッピー：ログイン画面
		driver.get(PC_LOGIN_URL);
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
			driver.quit();
			return point_count;
		} catch (Exception e) {
			// ネットチェック
			netCheck(driver);
			try{
				driver.quit();
			}catch(Exception e_d){
			}
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

package excute.ad_areas;

import static common.constant.CommonConstants.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import common.Point;
import common.enquete.Adsurvey_Enquete;
import excute.bean.AccountBean;


/**
 * =====================================================================================================================
 * 【モッピー】：クマクマ調査団
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Reados extends Point {

	/** 「daily-points」 */
	private static final String C_D_P = "daily-points";
	/** 「enquete_box」 */
	private static final String C_E_B = "enquete_box";
	/** 「クマクマ調査団URL」 */
	String reados_url;
	/** 「獲得済みポイント」 */
	int point_count = 0;
	/** 「アンケート件数」 */
	int enquete_count = 0;
	/** 「開始Index」 */
	int start = 0;
	/** 「終了Index」 */
	int end = 10;
	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();

	/**
	 * コンストラクタ
	 */
	public Moppy_Reados(){
	}
	/**
	 * =================================================================================================================
	 * クマクマ調査団メイン処理
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 * @param String
	 *            url
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
			// 「クマクマ調査団URL」
			reados_url = driver.findElement(By.className(C_D_P)).findElements(By.tagName(T_A)).get(INT_3).getAttribute(A_HREF);
			if(StringUtils.isNotEmpty(reados_url)){
				// 「クマクマ調査団画面」
				driver.get(reados_url);
				// アンケート件数
				int enquete_count = driver.findElement(By.className(C_E_B)).findElements(By.tagName(T_A)).size();
				// 「獲得ポイント」
				for (int i = 0; i < enquete_count; i++) {
					// 調査スタート
					start();
					// 「クマクマ調査団画面」
					driver.get(reados_url);
				}
			}else{
				System.out.println("【エラー】：クマクマ調査団URL取得失敗!");
			}
			driver.quit();
			return point_count;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：クマクマ調査団s失敗");
			return point_count;
		}

	}

	/**
	 * =================================================================================================================
	 * クマクマ調査スタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void start() {
		try {
			// 「AdsurveyアンケートURL」
			String enquete_url = driver.findElement(By.className(C_E_B)).findElements(By.tagName(T_A)).get(INT_0)
					.getAttribute(A_HREF);
			if(StringUtils.isNotEmpty(enquete_url)){
				// 「該当するAdsurveyアンケート」へ遷移する
				driver.get(enquete_url);
				// 「Adsurveyアンケート回答」
				if (Adsurvey_Enquete.execute(driver, bean)) {
					point_count += 10;
				}
			}else{
				System.out.println("【エラー】：AdsurveyアンケートURL取得失敗!");
			}

		} catch (Exception e) {
			System.out.println("【エラー】：Adsurveyアンケート回答失敗!");
			Adsurvey_Enquete.execute_restart(driver);
		}
	}
}

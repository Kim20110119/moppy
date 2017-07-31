package excute.sp;

import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import common.Sp_Point;
import common.enquete.Sp_Manga_enquete;
import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * 【モッピー】：動画
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Vedio extends Sp_Point {
	/** 「漫画アンケートURL」 */
	String enquete_url;
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
	public Sp_Moppy_Vedio(){
	}
	/**
	 * =================================================================================================================
	 * 漫画アンケートメイン処理
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
			driver.quit();
			return point_count;
		} catch (Exception e) {
			try{
				driver.quit();
			}catch(Exception e_d_q_2){
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
			Sp_Manga_enquete.execute(driver, bean);
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
			Sp_Manga_enquete.execute(driver, bean);
		} catch (Exception e) {
			System.out.println("【エラー】：漫画アンケート再スタート失敗！");
		}
	}
}

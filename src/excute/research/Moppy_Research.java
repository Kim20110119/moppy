package excute.research;

import static common.Common.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Point;
import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * モッピー：ポイントリサーチ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Research extends Point {
	/** 「pointResearch__box__btn」 */
	private static final String C_P_B_B = "pointResearch__box__btn";
	/** 「ui-button」 */
	private static final String C_U_B = "ui-button";
	/** 「ui-label-radio」 */
	private static final String C_U_L_R = "ui-label-radio";
	/** 「ui-label-checkbox」 */
	private static final String C_U_L_C = "ui-label-checkbox";
	/** 「ui-select」 */
	private static final String C_U_S = "ui-select";

	/** 「ポイントリサーチURL」 */
	String election_url;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;
	/** 「アンケート件数」 */
	int enquete_count = 0;
	/** 「開始Index」 */
	int start = 0;
	/** 「終了Index」 */
	int end = 20;

	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();

	/**
	 * コンストラクタ
	 */
	public Moppy_Research() {
	}

	/**
	 * =================================================================================================================
	 * ポイントリサーチ
	 * =================================================================================================================
	 *
	 * @param AccountBean bean アカウントBean
	 * @param Boolean loginFlag ログインフラグ
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
			// 「ポイントリサーチ」
			driver.get(PC_POINT_RESEARCH_URL);
			// 「モッピー」アンケート一覧画面へ遷移する
			enquete_count = getSize(getByClass(C_P_B_B));
			if(enquete_count < end){
				end = enquete_count;
			}
			for (int i = start; i < end; i++) {
				start();
				// 「ポイントリサーチ」
				driver.get(PC_POINT_RESEARCH_URL);
			}
			driver.quit();
			return point_count;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：ポイントリサーチ失敗");
			return point_count;
		}
	}

	/**
	 * =================================================================================================================
	 * 投票スタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void start() {
		try {
			String enquet_url = driver.findElements(By.className(C_P_B_B)).get(0).getAttribute(A_HREF);
			driver.get(enquet_url);
			// 「アンケートに進む」
			click(getByClass(C_U_B));
			// 「回答開始」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「性別」
			clickByIndex(getByClass(C_U_L_R), getIndex(bean.getSex()));
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「年齢」
			clickByIndex(getByClass(C_U_L_R), this.getAgeGroup());
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「未既婚」
			clickByIndex(getByClass(C_U_L_R), getIndex(bean.getMarried()));
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「住所」
			selectByIndex(getByClass(C_U_S), this.getPref());
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「職業」
			clickByIndex(getByClass(C_U_L_R), int_random(7));
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 「アンケート回答」
			for (int j = 0; j < 20; j++) {
				// 1秒待ち
				sleep(1500);
				int q_r_count = getSize(getByClass(C_U_L_R));
				int q_c_count = getSize(getByClass(C_U_L_C));
				int q_s_count = getSize(getByClass(C_U_S));
				if (q_r_count > 0) {
					clickByIndex(getByClass(C_U_L_R), int_random(q_r_count));
				} else if (q_c_count > 0) {
					clickByIndex(getByClass(C_U_L_C), int_random(q_c_count));
					clickByIndex(getByClass(C_U_L_C), int_random(q_c_count));
					clickByIndex(getByClass(C_U_L_C), int_random(q_c_count));
				} else if (q_s_count > 0) {
					selectByIndex(getByClass(C_U_S), int_random(3));
				} else {
					break;
				}
				// 1秒待ち
				sleep(1500);
				// 「次へ」
				click(getByClass(C_U_B));
			}
			// 1秒待ち
			sleep(1500);
			// 広告を閉じる
			ad_close(driver);
			// 「ポイント獲得」
			click(getByXpath(T_INPUT, A_VALUE, "ポイント獲得"));
			// 1秒待ち
			sleep(1500);
			ad_close(driver);
			// 「閉じる」
			driver.findElement(By.partialLinkText("閉じる"));
			point_count += 1;
		} catch (Exception e) {
			System.out.println("【エラー】：ポイントリサーチ失敗");
		}
	}

	/**
	 * =================================================================================================================
	 * 年齢帯を取得する
	 * =================================================================================================================
	 *
	 * @return Integer ageGroup 年齢帯
	 *
	 * @author kimC
	 *
	 */
	public Integer getAgeGroup() {
		Integer age = getintAge(bean.getYear());
		Integer ageGroup = 2;
		try{
			if(age <= 20){
				ageGroup = 0;
			}else if(age > 20 && age <= 30){
				ageGroup = 1;
			}else if(age > 30 && age <= 40){
				ageGroup = 2;
			}else if(age > 40 && age <= 50){
				ageGroup = 3;
			}else if(age > 50 && age <= 60){
				ageGroup = 4;
			}else{
				ageGroup = 5;
			}
		}catch (Exception e){
		}
		return ageGroup;
	}

	/**
	 * =================================================================================================================
	 * 地域コードを取得する
	 * =================================================================================================================
	 *
	 * @return Integer prefGroup 年齢帯
	 *
	 * @author kimC
	 *
	 */
	public Integer getPref() {
		Integer pref = getPrefList().indexOf(bean.getPref());
		Integer prefGroup = 6;
		try{
			if(pref <= 0){
				// 北海道
				prefGroup = 1;
			}else if(pref > 0 && pref <= 6){
				// 東北
				prefGroup = 3;
			}else if(pref > 6 && pref <= 14){
				// 関東・甲信越
				prefGroup = 4;
			}else if(pref > 14 && pref <= 17){
				// 北陸
				prefGroup = 2;
			}else if(pref > 17 && pref <= 29){
				prefGroup = 6;
			}else if(pref > 29 && pref <= 34){
				prefGroup = 7;
			}else if(pref > 34 && pref <= 38){
				prefGroup = 8;
			}else if(pref > 38 && pref <= 46){
				prefGroup = 9;
			}else{
				prefGroup = 10;
			}
		}catch (Exception e){
		}
		return prefGroup;
	}

	/**
	 * =================================================================================================================
	 * 広告を閉じる
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 *
	 * @author kimC
	 *
	 */
	public static  void ad_close(WebDriver driver) {
		try {
			driver.findElement(By.partialLinkText("閉じる")).click();
			// 0.8秒待ち
			Thread.sleep(1500);
		} catch (Exception e) {
		}
	}
}

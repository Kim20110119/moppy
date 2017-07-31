package excute.click_coin;

import static common.Common.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import common.Point;
import excute.bean.AccountBean;


/**
 * =====================================================================================================================
 * モッピー：毎日クリックコイン
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Click_Coin  extends Point {
	/** 「cc__bnr」 */
	private static final String C_C_B = "cc__bnr";
	/** 「cc-btn」 */
	private static final String C_C_B_N = "cc-btn";
	/** 「shop」 */
	private static final String C_S = "shop";
	/** 「lst_ts-area」 */
	private static final String I_L_T_A = "lst_ts-area";

	/** 「毎日クリックコインURL」 */
	String shindan_link;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「毎日クリックコイン開始番号」 */
	int start = 0;
	/** 「毎日クリックコイン終了番号」 */
	int end = 11;
	/** 「毎日クリックコインURL」 */
	String sindan_url;
	/** 「オリジンURL」 */
	String originalHandle;
	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();

	/**
	 * コンストラクタ
	 */
	public Moppy_Click_Coin() {
	}

	/**
	 * =================================================================================================================
	 * モッピー：毎日クリックコイン
	 * =================================================================================================================
	 *
	 * @return int point_couont 獲得済みポイント
	 *
	 * @author kimC
	 *
	 */
	public Integer execute(AccountBean pBean, Boolean loginFlag) {
		try{
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
			// 「毎日クリックコイン」
			driver.get(PC_CLICK_COIN_URL);
			// モッピー画面のタブ名を取得する
			originalHandle = driver.getWindowHandle();
			one_click();
			driver.quit();
			return point_count/10;
		}catch (Exception e){
			netCheck(driver);
			driver.quit();
			System.out.println("【エラー】：毎日クリック失敗");
			return point_count;
		}

	}

	/**
	 * =================================================================================================================
	 * モッピー：毎日クリック50コイン処理(キャンペン終了)
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void click() {
		try{
			// バナーをクリックする
			click(getByClass(C_C_B));
			// タブを移動する
			getUrlByTab();
			// 都道府県件数を取得する
			int pref_count = driver.findElement(By.id(I_L_T_A)).findElements(By.tagName(T_LI)).get(int_random(6)).findElements(By.tagName(T_A)).size();
			// 都道府県条件として、クリックする
			driver.findElement(By.id(I_L_T_A)).findElements(By.tagName(T_LI)).get(int_random(6)).findElements(By.tagName(T_A)).get(int_random(pref_count)).click();
			// 店件数を取得する
			int shop_count = driver.findElements(By.className(C_S)).size();
			// 店をクリックする
			driver.findElements(By.className(C_S)).get(int_random(shop_count)).findElements(By.tagName(T_A)).get(0).click();
		}catch (Exception e){

		}
	}

	/**
	 * =================================================================================================================
	 * モッピー：毎日クリック1コイン処理
	 * =================================================================================================================
	 *
	 *
	 * @param String originalHandel モッピー画面タグ名
	 *
	 * @author kimC
	 *
	 */
	public void one_click() {
		try{
			int one_click_count = getSize(getByClass(C_C_B_N));
			for(int i = 0; i < one_click_count; i ++){
				driver.findElements(By.className(C_C_B_N)).get(i).click();
			}
		}catch (Exception e){

		}
	}

	/**
	 * =================================================================================================================
	 * タブURLを取得する
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 * @param String
	 *            originalHandle
	 * @param By
	 *            by
	 *
	 * @author kimC
	 *
	 */
	public String getUrlByTab() {
		String tab_url = StringUtils.EMPTY;
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandle)) {
					driver.switchTo().window(handle);
					tab_url = driver.getCurrentUrl();
					return tab_url;
				}
			}
			return tab_url;
		} catch (Exception e) {
			return tab_url;
		}

	}

	/**
	 * =================================================================================================================
	 * タブを閉じる
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 * @param String
	 *            originalHandle
	 * @param By
	 *            by
	 *
	 * @author kimC
	 *
	 */
	public void tab_close() {
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandle)) {
					driver.switchTo().window(handle);
					driver.close();
				}
			}
			driver.switchTo().window(originalHandle);

		} catch (Exception e) {
		}

	}

}

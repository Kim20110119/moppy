package excute.game;

import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Point;
import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * モッピー：モッピーガチャ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Game extends Point {
	/** 「click-start」 */
	private static final String C_C_S = "click-start";
	/** 「ver」 */
	private static final String C_V = "ver";
	/** 「click-end」 */
	private static final String C_C_E = "click-end";

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
	int end = 10;
	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();
	/** 「アンケートIndex」 */
	int index = 0;
	/** WindowsID */
	String originalHandel;

	/**
	 * コンストラクタ
	 */
	public Moppy_Game() {
	}

	/**
	 * =================================================================================================================
	 * モッピーガチャ
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
				originalHandel = driver.getWindowHandle();
			}
			// 「モッピーガチャ」
			driver.get(PC_GACHA_URL);
			// 「いますぐ遊ぶ」
			click(getByClass(C_C_S));
			// 1秒待ち
			Thread.sleep(1000);
			// 「クリック」
			click(getByClass(C_V));
			// 2秒待ち
			Thread.sleep(2000);
			// 「結果を見る」
			click(getByClass(C_C_E));
			// 1.5秒待ち
			Thread.sleep(1500);
			// 「広告を閉じる」
			ad_close(driver);
			// 1.5秒待ち
			Thread.sleep(1500);
			// 「`広告をクリック
			click(getByXpath(T_IMG, A_ALT, "バナー"));
			// 1.5秒待ち
			Thread.sleep(1500);
			driver.quit();
			return point_count;
		} catch (Exception e) {
			try{
				driver.quit();
			}catch(Exception e_d){
			}
			System.out.println("【エラー】：モッピーガチャ失敗");
			return point_count;
		}
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
	public static void ad_close(WebDriver driver) {
		try {
			driver.findElement(By.className("delete")).click();
			// 0.8秒待ち
			Thread.sleep(1500);
		} catch (Exception e) {
		}
	}

	/**
	 * =================================================================================================================
	 * タブを閉じる
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void tab_close() {
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandel)) {
					driver.switchTo().window(handle);
					driver.close();
				}
			}
			driver.switchTo().window(originalHandel);
		} catch (Exception e) {
			System.out.println("【エラー】：タブを閉じる処理が失敗しました。");
		}

	}
}

package excute.shindan;

import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.openqa.selenium.By;

import common.Point;
import common.shindan.WebShindan;
import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * モッピー：WEB診断
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Shindan  extends Point {
	/** 「クマクマ総選挙URL」 */
	String shindan_link;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;
	/** 「WEB診断開始番号」 */
	int start = 0;
	/** 「WEB診断終了番号」 */
	int end = 10;
	/** 「WEB診断URL」 */
	String sindan_url;
	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();

	/**
	 * コンストラクタ
	 */
	public Moppy_Shindan() {
	}

	/**
	 * =================================================================================================================
	 * モッピー：WEB診断
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
			// 「WEB診断」
			driver.get(PC_GAMECONTENTS_URL);
			// WEB診断URLを取得する
			shindan_link = driver.findElement(By.partialLinkText("診断")).getAttribute(A_HREF);
			// WEB診断画面へ遷移する
			driver.get(shindan_link);
			// 1秒待ち
			sleep(1000);
			// WEB診断URLを取得する
			shindan_link = driver.getCurrentUrl();
			for(int i = start; i < end; i++){
				// 0.5秒待ち
				sleep(500);
				// 診断URL
				sindan_url = driver.findElements(By.xpath("//a[@role='button']")).get(i).getAttribute(A_HREF);
				// WEB診断
				driver.get(sindan_url);
				if(start()){
					point_count += 1;
				}else{
					restart();
				}
				driver.get(shindan_link);
			}
			driver.quit();
			return point_count;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：WEB診断失敗");
			return point_count;
		}

	}

	public Boolean start() {
		try {
			WebShindan.execute(driver);
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：WEB診断失敗");
			return Boolean.FALSE;
		}

	}

	public void restart() {
		try {
			// WEB診断
			driver.get(sindan_url);
			WebShindan.execute(driver);
			point_count += 1;
		} catch (Exception e) {
			System.out.println("【エラー】：WEB診断再スタート失敗");
		}

	}

}

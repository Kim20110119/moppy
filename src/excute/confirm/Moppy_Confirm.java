package excute.confirm;

import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import common.Point;
import excute.bean.AccountBean;
import excute.excel.Output;

/**
 * =====================================================================================================================
 * モッピー：確認
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Confirm extends Point {
	/** 「アカウントBean」 */
	AccountBean bean = new AccountBean();
	/** 「診断URL」 */
	String shindan_url  = StringUtils.EMPTY;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;
	/** 「WEB診断開始番号」 */
	int start = 0;
	/** 「WEB診断終了番号」 */
	int end = 50;
	/** 「出力アカウントリスト」 */
	List<AccountBean> outputList = new ArrayList<AccountBean>();

	/**
	 * コンストラクタ
	 */
	public Moppy_Confirm() {
		// Chromeドライバーをプロパティへ設定
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
	}

	/**
	 * =================================================================================================================
	 * モッピー：獲得ポイント確認
	 * =================================================================================================================
	 *
	 * @return int point_couont 獲得済みポイント
	 *
	 * @author kimC
	 *
	 */
	public Integer execute(List<AccountBean> list) {
		for (int i = 0; i < list.size(); i++) {
			// アカウントBean
			bean = list.get(i);
			// 「登録URL」
			driver.get(PC_LOGIN_URL);
			// 0.5秒待ち
			sleep(500);
			// モッピー：ログイン画面
			driver.get(PC_LOGIN_URL);
			// モッピー：ログインメールアドレス
			sendkeysByStr(getByName(V_MAIL), bean.getMail());
			// モッピー：ログインパスワード
			sendkeysByStr(getByName(V_PASS), bean.getPassword());
			// モッピー：ログインボタン
			click(getByXpath(T_BUTTON, A_TYPE, V_SUBMIT));
			this.sleep(5000);
			// 獲得済みポイントを取得し設定する
			String point = driver.findElement(By.className("odometer-inside")).getText();
			bean.setPoint(point.replaceAll("\n", ""));
			outputList.add(bean);
			// Wifiを再起動
			this.wifiRestart();
			// ブラウザを終了する
			driver.quit();
		}
		// アカウント情報をEXCELで出力する
		this.output_account();
		return point_count;
	}

	/**
	 * =================================================================================================================
	 * 獲得ポイント情報を出力する
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void output_account() {
		try{
			Output output = new Output();
			output.execute(outputList);
			System.out.println("獲得ポイント出力成功！");
		}catch (Exception e) {
			System.out.println("【エラー】：獲得ポイント出力失敗！");
		}
	}

	/**
	 * =================================================================================================================
	 * Wifi再起動
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void wifiRestart() {
		driver.get("http://admin:20110119Jjz@192.168.179.1/index.cgi/reboot_main");
		driver.findElement(By.id("UPDATE_BUTTON")).click();
		driver.switchTo().alert().accept();
		sleep(100000);
		driver.switchTo().alert().accept();
	}

	/**
	 * sleep処理
	 *
	 * @param long ミリ秒数
	 * @author kimC
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

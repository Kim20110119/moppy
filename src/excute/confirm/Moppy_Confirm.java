package excute.confirm;

import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
public class Moppy_Confirm {
	// WEBドライバー
		public WebDriver driver;
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
			try{
				// Chromeドライバー
				driver = new ChromeDriver();
			}catch(Exception e_q){
				// Chromeドライバー
				driver = new ChromeDriver();
			}
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
			try{
				// 獲得済みポイントを取得し設定する
				String point = driver.findElement(By.className("odometer-inside")).getText();
				bean.setPoint(point.replaceAll("\n", ""));
				outputList.add(bean);
				// Wifiを再起動
				this.wifiRestart();
			}catch (Exception e){
				System.out.println("【エラー】：" + bean.getMail()+"ログイン失敗！");
			}
			try{
				// ブラウザを終了する
				driver.quit();
			}catch(Exception e_q){
			}
			
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
	
	/**
	 * クリック処理
	 *
	 * @param By
	 *            locator
	 */
	public void click(By locator) {
		driver.findElement(locator).click();
	}

	/**
	 * クリック処理
	 *
	 * @param By
	 *            locator
	 * @param int
	 *            index
	 *
	 */
	public void clickByIndex(By locator, int index) {
		driver.findElements(locator).get(index).click();
	}

	/**
	 * フォームのサブミット処理
	 *
	 * @param By
	 *            locator
	 */
	public void submit(By locator) {
		driver.findElement(locator).submit();
	}

	/**
	 * テキストフィールドの入力処理
	 *
	 * @param By
	 *            locator
	 * @param String
	 *            text
	 */
	public void sendkeysByStr(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}

	/**
	 * プルダウンの選択処理
	 *
	 * @param By
	 *            locator
	 * @param String
	 *            label
	 */
	public void selectByText(By locator, String label) {
		Select element = new Select(driver.findElement(locator));
		element.selectByVisibleText(label);
	}

	/**
	 * プルダウンの選択処理
	 *
	 * @param By
	 *            locator
	 * @param int
	 *            index
	 */
	public void selectByIndex(By locator, int index) {
		Select element = new Select(driver.findElement(locator));
		element.selectByIndex(index);
	}

	/**
	 * プルダウンの選択処理
	 *
	 * @param By
	 *            locator
	 * @param String
	 *            value
	 */
	public void selectByValue(By locator, String value) {
		Select element = new Select(driver.findElement(locator));
		element.selectByValue(value);
	}

	/**
	 * エレメントサイズ取得処理
	 *
	 * @param By
	 *            locator
	 */
	public int getSize(By locator) {
		return driver.findElements(locator).size();
	}

	/**
	 * xPath取得処理
	 *
	 * @param String
	 *            tag
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return String xPath
	 *
	 */
	public String getXpath(String tag, String key, String value) {
		return "//" + tag + "[@" + key + "='" + value + "']";

	}

	/**
	 * ByxPath取得処理
	 *
	 * @param String
	 *            tag
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return By xPath
	 *
	 */
	public By getByXpath(String tag, String key, String value) {
		return By.xpath(getXpath(tag, key, value));
	}

	/**
	 * ByxClassName取得処理
	 *
	 * @param String
	 *            className
	 *
	 * @return By className
	 *
	 */
	public By getByClass(String className) {
		return By.className(className);
	}

	/**
	 * ByxTagName取得処理
	 *
	 * @param String
	 *            tag
	 *
	 * @return By tagName
	 *
	 */
	public By getByTag(String tag) {
		return By.tagName(tag);
	}

	/**
	 * ByxId取得処理
	 *
	 * @param String
	 *            id
	 *
	 * @return By id
	 *
	 */
	public By getById(String id) {
		return By.id(id);
	}

	/**
	 * ByxName取得処理
	 *
	 * @param String
	 *            name
	 *
	 * @return By name
	 *
	 */
	public By getByName(String name) {
		return By.name(name);
	}

}

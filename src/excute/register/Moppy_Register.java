package excute.register;

import static common.Common.*;
import static common.constant.MoppyConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import excute.bean.AccountBean;
import excute.excel.Output;
import excute.research.Moppy_Research;

/**
 * =====================================================================================================================
 * モッピー：登録
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Register{
	/** 「WEBドライバー」 */
	WebDriver driver;
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
	public Moppy_Register() {
		// Chromeドライバーをプロパティへ設定
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
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
	public Integer execute(List<AccountBean> list) {
		for (int i = 0; i < list.size(); i++) {
			// アカウントBean
			bean = list.get(i);
			// Chromeドライバー
			driver = new ChromeDriver();
			// 「登録URL」
			driver.get(PC_REGISTER_URL);
			// 0.5秒待ち
			sleep(500);
			// 仮登録
			if(!this.register()){
				continue;
			}
			// メール確認
			if(!this.mail_confirm()){
				continue;
			}
			// アカウント情報登録
			if(!this.input()){
				continue;
			}
//			// メールからアクセス
//			this.mail_access();
			// モッピーリサーチ
			this.outputList.add(bean);
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
	 * モッピー：仮登録
	 * =================================================================================================================
	 *
	 * @return Boolean 処理結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean register() {
		try {
			// 「無料会員登録URL」
			driver.get(PC_SELECT_URL);
			// メールアドレス
			driver.findElement(By.id("mail")).sendKeys(bean.getMail());
			// 「無料会員登録」ボタン
			driver.findElement(By.id("submit")).click();
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：モッピー仮登録失敗");
			return Boolean.FALSE;
		}

	}

	/**
	 * =================================================================================================================
	 * モッピー：メール確認
	 * =================================================================================================================
	 *
	 * @return Boolean 処理結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean mail_confirm() {
		try {
			// 使い捨てメール画面
			driver.get(MAIL_URL);
			// 「他のアカウントにログイン（復元／同期）」をクリック
			driver.findElement(By.id("link_loginform")).click();
		    // 「ID」を入力する
			driver.findElement(By.id("user_number")).sendKeys(MAIL_ID);
		    // 「パスワード」を入力する
			driver.findElement(By.id("user_password")).sendKeys(MAIL_PASS);
		    // 1秒待ち
			sleep(1000);
		    // 「ログインする」ボタンのクリック
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("checkLogin();");
			// 1秒待ち
			sleep(1000);
		    // 「確認」をクリックする
			jse.executeScript("okConfirmDialog();");
			// 1秒待ち
			sleep(1000);
			driver.switchTo().alert().accept();
			// 1秒待ち
			sleep(1000);
			driver.switchTo().alert().accept();
			// 1秒待ち
			sleep(1000);
			// 「受信トレイ」をクリックする
			jse.executeScript("location.href='recv.php';");
			// 1秒待ち
			sleep(1000);
			// メールIDを取得する
			String mail_id = driver.findElements(By.className("ui-listview")).get(1).findElement(By.tagName("li")).getAttribute("id");
			// メール番号を取得する
			String mail_num = mail_id.split("_", 0)[2];
			// メール詳細URLを取得する
			String mail_detail_url = "https://m.kuku.lu/smphone.app.recv.data.php?UID=a73ac4dcc47e2fb4827d093a9416c679&num=" + mail_num + "&detailmode=1";
			// メール内容詳細参照用ドライバー
			WebDriver mail_detail = new ChromeDriver();
			// メール内容詳細参照へ遷移する
			mail_detail.get(mail_detail_url);
			// 「トラフィック」仮登録用URLを取得する
			String moppy_register_url = mail_detail.findElement(By.partialLinkText("https://ssl.pc.moppy.jp/entry/regist_form.php")) .getText();
			// メール詳細ドライバーを終了する
			mail_detail.quit();
			// モッピー会員登録画面へ遷移する
			driver.get(moppy_register_url);
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：モッピーメール確認失敗");
			return Boolean.FALSE;
		}

	}

	/**
	 * =================================================================================================================
	 * モッピー：会員情報登録
	 * =================================================================================================================
	 *
	 * @return Boolean 処理結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean input() {
		try {
			// ニックネーム
			driver.findElement(By.name("nick")).sendKeys(bean.getNick());
			// パスワード
			driver.findElement(By.name("password")).sendKeys(bean.getPassword());
			// パスワード確認
			driver.findElement(By.name("repassword")).sendKeys(bean.getPassword());
			// 性別
			driver.findElements(By.name("sex")).get(getIndex(bean.getSex())).click();
			// 年
			Select year = new Select(driver.findElement(By.name("birthday_y")));
			year.selectByValue(bean.getYear());
			// 月
			Select month = new Select(driver.findElement(By.name("birthday_m")));
			month.selectByValue(bean.getMonth());
			// 都道府県
			Select pref = new Select(driver.findElement(By.name("pref")));
			pref.selectByVisibleText(bean.getPref());
			// 未既婚
			driver.findElements(By.name("is_married")).get(getIndex(bean.getMarried())).click();
			// 秘密の質問
			Select codeid = new Select(driver.findElement(By.name("codeid")));
			codeid.selectByValue(bean.getCodeid());
			// 秘密の答え
			driver.findElement(By.name("codeword")).sendKeys(bean.getCodeword());
			// 「利用規約に同意して確認画面へ」
			driver.findElement(By.className("btn-resist")).click();
			// 「上記内容を登録する」
			driver.findElement(By.className("btn-resist")).click();
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：モッピーメール確認失敗");
			return Boolean.FALSE;
		}

	}

	/**
	 * =================================================================================================================
	 * モッピー：メールリンクからアクセス
	 * =================================================================================================================
	 *
	 * @return Boolean 処理結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean mail_access() {
		try {
			// 使い捨てメール画面
			driver.get(MAIL_URL);
		    // 「ログインする」ボタンのクリック
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// 1秒待ち
			sleep(1000);
			// 「受信トレイ」をクリックする
			jse.executeScript("location.href='recv.php';");
			// 1秒待ち
			sleep(1000);
			String mail_id = driver.findElements(By.className("ui-listview")).get(1).findElement(By.tagName("li")).getAttribute("id");
			String mail_num = mail_id.split("_", 0)[2];
			String mail_detail_url = "https://m.kuku.lu/smphone.app.recv.data.php?UID=a73ac4dcc47e2fb4827d093a9416c679&num=" + mail_num + "&detailmode=1";
			// メール内容詳細参照用ドライバー
			WebDriver mail_detail = new ChromeDriver();
			// メール内容詳細参照へ遷移する
			mail_detail.get(mail_detail_url);
			// 「トラフィック」アクセス用URLを取得する
			String moppy_access_url = mail_detail.findElement(By.partialLinkText("http://pc.moppy.jp/cl")) .getText();
			mail_detail.quit();
			driver.get(moppy_access_url);
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：モッピーアクセスメール獲得失敗");
			return Boolean.FALSE;
		}

	}

	/**
	 * =================================================================================================================
	 * モッピー：モッピーリサーチ
	 * =================================================================================================================
	 *
	 * @return Boolean 処理結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean research() {
		try {
			Moppy_Research research = new Moppy_Research();
			research.execute(bean, Boolean.TRUE);
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：モッピーアクセスメール獲得失敗");
			return Boolean.FALSE;
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

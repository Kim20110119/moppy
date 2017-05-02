package excute.mail;

import static common.constant.MoppyConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * モッピー：メール確認
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Mail{
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
	/** 「ID」 */
	String id  = "";
	/** 「パスワード」 */
	String pass  = "";
	/** 「UID」 */
	String uid  = "";

	/**
	 * コンストラクタ
	 */
	public Moppy_Mail(String pId, String pPass, String pUid) {
		// Chromeドライバーをプロパティへ設定
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		this.id = pId;     // ID
		this.pass = pPass; // パスワード
		this.uid = pUid;   // UID
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
	public Integer execute() {
		// Chromeドライバー
		driver = new ChromeDriver();
		// メール確認
		this.mail_confirm();
		// ブラウザを終了する
		driver.quit();
		return point_count;
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
			// ログイン
			this.login();
			// 「受信トレイ」をクリックする
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("location.href='recv.php';");
			for(int k = 0; k < 20; k++){
				// 1秒待ち
				sleep(1000);
				int count = driver.findElements(By.className("ui-listview")).size();
				for(int i = 1; i < count; i++){
					// メールIDを取得する
					String mail_id = driver.findElements(By.className("ui-listview")).get(i).findElement(By.tagName("li")).getAttribute("id");
					// メール番号を取得する
					String mail_num = mail_id.split("_", 0)[2];
					// メール詳細URLを取得する
					String mail_detail_url = "https://m.kuku.lu/smphone.app.recv.data.php?UID=" + this.uid + "&num=" + mail_num + "&detailmode=1";
					// メール内容詳細参照用ドライバー
					WebDriver mail_detail = new ChromeDriver();
					// メール内容詳細参照へ遷移する
					mail_detail.get(mail_detail_url);
					// 「トラフィック」仮登録用URLを取得する
					String moppy_register_url = StringUtils.EMPTY;
					try{
						int url_count = mail_detail.findElements(By.partialLinkText("http://pc.moppy.jp/clc/?clc_tag")).size();
						for(int j = 0; j < url_count; j++){
							moppy_register_url = mail_detail.findElements(By.partialLinkText("http://pc.moppy.jp/clc/?clc_tag")).get(j).getText();
							// モッピー会員登録画面へ遷移する
							mail_detail.get(moppy_register_url);
						}

					}catch(Exception e){
					}
					// メール詳細ドライバーを終了する
					mail_detail.quit();
				}
				if(this.check()){
					break;
				}
				this.mail_delete();
			}

			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("【エラー】：モッピーメール確認失敗");
			return Boolean.FALSE;
		}

	}

	/**
	 * =================================================================================================================
	 * ぽいぽいメールログイン
	 * =================================================================================================================
	 *
	 * @return Boolean ぽいぽいメールログイン
	 *
	 * @author kimC
	 *
	 */
	public void login() {
		try {
			// 使い捨てメール画面
			driver.get(MAIL_URL);
			// 「他のアカウントにログイン（復元／同期）」をクリック
			driver.findElement(By.id("link_loginform")).click();
		    // 「ID」を入力する
			driver.findElement(By.id("user_number")).sendKeys(this.id);
		    // 「パスワード」を入力する
			driver.findElement(By.id("user_password")).sendKeys(this.pass);
		    // 「ログインする」ボタンのクリック
			// 「ログインする」ボタンのクリック
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("checkLogin();");
			// 1秒待ち
			sleep(1500);
		    // 「確認」をクリックする
			jse.executeScript("okConfirmDialog();");
			// 1.5秒待ち
			sleep(1500);
			driver.switchTo().alert().accept();
			// 3秒待ち
			sleep(3000);
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("【エラー】：ログイン失敗");
			// 1秒待ち
			sleep(1000);
			driver.switchTo().alert().accept();
		}
	}

	/**
	 * =================================================================================================================
	 * チャンスイット：メール削除
	 * =================================================================================================================
	 *
	 * @return Boolean 処理結果
	 *
	 * @author kimC
	 *
	 */
	public void mail_delete() {
		try{
			// 0.5秒待ち
			sleep(500);
			// 「ALL」
			driver.findElement(By.xpath("//img[@src='img/chkall.png']")).click();
			// 「削除」
			driver.findElement(By.id("link_checkmenu_delele")).click();
			// 0.5秒待ち
			sleep(500);
			driver.switchTo().alert().accept();
			// 5秒待ち
			sleep(5000);
		}catch (Exception e){
			System.out.println("【エラー】：メールアドレス削除失敗");
			try{
			// 次のページへ遷移
			driver.findElement(By.id("link_page_next")).click();
			}catch(Exception n_e){
			}
		}
	}

	/**
	 * =================================================================================================================
	 * 削除済み確認
	 * =================================================================================================================
	 *
	 * @return Boolean 削除済みフラグ結果
	 *
	 * @author kimC
	 *
	 */
	public Boolean check() {
		try {
			Boolean flag = Boolean.FALSE;
			String message = driver.findElement(By.xpath("//div[@data-role='content']")).getText();
			if(message.matches(".*受信したメールがありません。.*")){
				flag = Boolean.TRUE;
			}
			return flag;
		} catch (Exception e) {
			System.out.println("【エラー】：メールアドレス削除済み確認失敗");
			return Boolean.FALSE;
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

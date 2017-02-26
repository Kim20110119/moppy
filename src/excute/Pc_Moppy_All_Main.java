package excute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excute.ad_areas.Moppy_Ad_Enquete;
import excute.ad_areas.Moppy_Reados;
import excute.bean.AccountBean;
import excute.click_coin.Moppy_Click_Coin;
import excute.excel.Account;
import excute.research.Moppy_Research;
import excute.shindan.Moppy_Shindan;
import excute.sp.Sp_Moppy_Reados;
import excute.sp.Sp_Moppy_Shindan;

/**
 * =====================================================================================================================
 * 【モッピー】：自動化
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_All_Main {
	// 【モッピー】：自動化
	public static void main(String[] args) {
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			// 「クマクマ調査団」
			Moppy_Reados reados = new Moppy_Reados();
			reados.execute(bean, Boolean.TRUE);
			// 「リサーチアンケート」
			Moppy_Research research = new Moppy_Research();
			research.execute(bean, Boolean.TRUE);
			// 「漫画アンケート」
			Moppy_Ad_Enquete enquete = new Moppy_Ad_Enquete();
			enquete.execute(bean, Boolean.TRUE);
			// 「毎日クリック」
			Moppy_Click_Coin mcc = new Moppy_Click_Coin();
			mcc.execute(bean, Boolean.TRUE);
			// 「WEB診断」
			Moppy_Shindan shindan = new Moppy_Shindan();
			shindan.execute(bean, Boolean.TRUE);
			// 「WEB診断」(携帯)
			Sp_Moppy_Shindan sp_shindan = new Sp_Moppy_Shindan();
			sp_shindan.execute(bean, Boolean.TRUE);
			// 「クマクマ調査団」(携帯)
			Sp_Moppy_Reados sp_reados = new Sp_Moppy_Reados();
			sp_reados.execute(bean, Boolean.TRUE);
			wifiRestart();
		}
		System.out.println("【モッピー】：自動化終了。");
	}
	
	/**
	 * =================================================================================================================
	 * Wifi再起動
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public static void wifiRestart() {
		try{
			// Chromeドライバーをプロパティへ設定
			System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
			// Chromeドライバー
			WebDriver driver = new ChromeDriver();
			driver.get("http://admin:20110119Jjz@192.168.179.1/index.cgi/reboot_main");
			driver.findElement(By.id("UPDATE_BUTTON")).click();
			driver.switchTo().alert().accept();
			sleep(100000);
			driver.switchTo().alert().accept();
			driver.quit();
		}catch (Exception e){
		}
	}
	
	/**
	 * sleep処理
	 *
	 * @param long ミリ秒数
	 * @author kimC
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

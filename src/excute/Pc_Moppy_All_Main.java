package excute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.research.Moppy_Research;

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
		for(AccountBean bean : account.execute(args[0])){
			for(int i = 0; i < 5; i++){
				// 「リサーチアンケート」
				Moppy_Research research = new Moppy_Research();
				research.execute(bean, Boolean.TRUE);	
			}
			if(args[1].equals("1")){
				wifiRestart();
			}
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
		// Chromeドライバーをプロパティへ設定
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		// Chromeドライバー
		WebDriver driver = new ChromeDriver();
		try{
			driver.get("http://admin:20110119Jjz@192.168.179.1/index.cgi/reboot_main");
			driver.findElement(By.id("UPDATE_BUTTON")).click();
			driver.switchTo().alert().accept();			
			sleep(100000);
			driver.switchTo().alert().accept();
			driver.quit();
		}catch (Exception e){
			driver.quit();
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

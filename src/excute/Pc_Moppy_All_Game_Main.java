package excute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.game.Moppy_Game;
import excute.sp.Sp_Moppy_Game;

/**
 * =====================================================================================================================
 * モッピー：ガチャ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_All_Game_Main {

	public static void main(String[] args) {
		Account account = new Account();
		int i = 1;
		for(AccountBean bean : account.execute(args[0])){
			Moppy_Game game = new Moppy_Game();
			game.execute(bean, Boolean.TRUE);
			Sp_Moppy_Game sp_game = new Sp_Moppy_Game();
			sp_game.execute(bean, Boolean.TRUE);
			if(args[1].equals("1")){
				if(i % 10 == 0){
					wifiRestart();
				}
			}
			i++;
		}
		System.out.println("【モッピー】：ガチャ終了。");
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
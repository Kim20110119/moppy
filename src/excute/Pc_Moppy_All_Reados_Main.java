package excute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excute.ad_areas.Moppy_Reados;
import excute.sp.Sp_Moppy_Reados;
import excute.bean.AccountBean;
import excute.excel.Account;

/**
 * =====================================================================================================================
 * 【モッピー】：クマクマ調査団
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_All_Reados_Main {
	// 【モッピー】：クマクマ調査団
	public static void main(String[] args) {
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			System.out.println(bean.getMail()+"自動化開始");		
			for(int i = 0; i < 10; i++){
				// 「クマクマ調査団」(携帯)
				Sp_Moppy_Reados sp_reados = new Sp_Moppy_Reados();
				int sp_count = sp_reados.execute(bean, Boolean.TRUE);
				if(sp_count == 0){
					break;
				}
				if(sp_count > 1){
					if(args[1].equals("1")){
						wifiRestart();
					}
				}
			}
			for(int i = 0; i < 10; i++){
				// 「クマクマ調査団」
				Moppy_Reados reados = new Moppy_Reados();
				int pc_count = reados.execute(bean, Boolean.TRUE);
				if(pc_count == 0){
					break;
				}
				if(pc_count > 1){
					if(args[1].equals("1")){
						wifiRestart();
					}
				}
				
			}
			if(args[1].equals("1")){
				wifiRestart();
			}
		}
		System.out.println("【モッピー】：「クマクマ調査団」自動化終了。");
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
			sleep(60000);
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

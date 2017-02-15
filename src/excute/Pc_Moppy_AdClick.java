package excute;

import static common.Common.*;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * =====================================================================================================================
 * モッピー：クリックキャンペン
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_AdClick {

	/**
	 * =====================================================================================================================
	 * モッピー：クリックキャンペン
	 * =====================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public static void main(String[] args) {
		try {

			// option.addArguments(
			// "--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS
			// X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0
			// Mobile/10A5376e Safari/8536.25");
			List<String> proxyList = getProxyList();
			for(int i = 0; i < proxyList.size(); i++){
				System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--proxy-server=http://"+proxyList.get(i));
				option.addArguments("--proxy-bypass-list=localhost");
				WebDriver driver = new ChromeDriver(option);
				excute(driver);
				// 5秒待ち
				Thread.sleep(5000);
				driver.quit();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * =====================================================================================================================
	 * モッピー紹介URLへ遷移する
	 * =====================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public static void excute(WebDriver driver) {
		try{
			driver.get("http://moppy.jp/top.php?xGVJe1dd");
		}catch (Exception e){
		}
	}

}

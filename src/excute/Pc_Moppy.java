package excute;

import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import common.Point;

public class Pc_Moppy extends Point  {

	public Pc_Moppy() {
		// モッピー：ログイン画面
		driver.get(PC_LOGIN_URL);
	}

	/**
	 * =================================================================================================================
	 * タブを閉じる
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 * @param String
	 *            originalHandle
	 * @param By
	 *            by
	 *
	 * @author kimC
	 *
	 */
	public static String getUrlByTab(WebDriver driver, String originalHandle) {
		String tab_url = StringUtils.EMPTY;
		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandle)) {
					driver.switchTo().window(handle);
					tab_url = driver.getCurrentUrl();
					return tab_url;
				}
			}
			return tab_url;
		} catch (Exception e) {
			return tab_url;
		}

	}

}

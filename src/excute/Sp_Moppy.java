package excute;

import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import common.Sp_Point;

public class Sp_Moppy extends Sp_Point  {

	public Sp_Moppy() {
		// モッピー：ログイン画面
		driver.get(PC_LOGIN_URL);
		// モッピー：ログインメールアドレス
		sendkeysByStr(getByName(V_MAIL), PC_LOGIN_MAIL);
		// モッピー：ログインパスワード
		sendkeysByStr(getByName(V_PASS), PC_LOGIN_PASSWORD);
		// モッピー：ログインボタン
		click(getByXpath(T_BUTTON, A_TYPE, V_SUBMIT));
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

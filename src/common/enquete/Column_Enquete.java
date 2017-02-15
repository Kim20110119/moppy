package common.enquete;

import static common.Common.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * =====================================================================================================================
 * 共通：Columnアンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Column_Enquete {

	/** 「btn」 */
	private static final String C_BTN = "btn";
	/** 「btn_regular」 */
	private static final String C_BTN_R = "btn_regular";

	/**
	 * =================================================================================================================
	 * Columnアンケート
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 * @return execute_flag
	 *
	 * @author kimC
	 *
	 */
	public static Boolean execute(WebDriver driver) throws Exception {
		String originalHandle = driver.getWindowHandle();
		// 「進む」
		driver.findElement(By.xpath("//input[@alt='進む']")).click();
		// 「次へ進む」
		driver.findElement(By.xpath("//input[@alt='次へ進む']")).click();
		// 「コラムを読む」
		driver.findElement(By.xpath("//input[@alt='コラムを読む']")).click();
		// 1.5秒待ち
		Thread.sleep(1500);
		// 「コラムを読む」
		for (int i = 0; i < 6; i++) {
			int next_count = driver.findElements(By.xpath("//input[@value='next']")).size();
			if (next_count > 0) {
				// 「進む」
				driver.findElement(By.xpath("//input[@value='next']")).click();
				// タブを閉じる
				tab_close(driver, originalHandle, By.xpath("//input[@value='next']"));
				// 1.5秒待ち
				Thread.sleep(1500);
			} else {
				break;
			}
		}
		// 広告を閉じる
		ad_close(driver);
		// タブを閉じる
		tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
		// 広告を閉じる
		ad_close(driver);
		// タブを閉じる
		tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
		// 広告を閉じる
		ad_close(driver);
		// タブを閉じる
		tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
		// 「進む」
		driver.findElement(By.xpath("//input[@alt='進む']")).click();
		// 1.5秒待ち
		Thread.sleep(1500);
		// 「性別」
		driver.findElement(By.id("que1")).click();
		// 「次へ」
		driver.findElement(By.xpath("//input[@value='次へ']")).click();
		// タブを閉じる
		tab_close(driver, originalHandle, By.xpath("//input[@value='next']"));
		// 3秒待ち
		Thread.sleep(3000);
		// 「年齢」
		driver.findElement(By.id("que3")).click();
		// 「次へ」
		driver.findElement(By.xpath("//input[@value='次へ']")).click();
		// タブを閉じる
		tab_close(driver, originalHandle, By.xpath("//input[@value='next']"));
		// 3秒待ち
		Thread.sleep(3000);
		// 「アンケート」
		for (int i = 0; i < 10; i++) {
			int radio_count = driver.findElements(By.xpath("//input[@type='radio']")).size();
			if (radio_count > 0) {
				String str_id = "que" + int_random(radio_count);
				driver.findElement(By.id(str_id)).click();
				// 「次へ」
				driver.findElement(By.xpath("//input[@value='次へ']")).click();
				// タブを閉じる
				tab_close(driver, originalHandle, By.xpath("//input[@value='次へ']"));
				// 3秒待ち
				Thread.sleep(3000);
			} else {
				// 広告を閉じる
				ad_close(driver);
				// 「進む」
				driver.findElement(By.xpath("//input[@alt='進む']")).click();
				// タブを閉じる
				tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
				// タブを閉じる
				tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
				break;
			}

		}

		return Boolean.TRUE;
	}

	/**
	 * =================================================================================================================
	 * Adsurveyアンケート再スタート
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 * @return execute_flag
	 *
	 * @author kimC
	 *
	 */
	public static Boolean execute_restart(WebDriver driver) {
		try {
			// 「次へ」ボタン
			driver.findElement(By.className("btn_regular")).click();
			// 0.5秒待ち
			Thread.sleep(500);
			// 「アンケート質問」
			for (int line = 1; line < 9; line++) {
				// 「ラジオボタン」
				int radio_count = driver.findElements(By.xpath("//input[@type='radio']")).size();
				// 「チェックボックス」
				int checkbox_count = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
				if (radio_count > 0) {
					driver.findElements(By.xpath("//input[@type='radio']")).get(int_random(radio_count)).click();
					// 「次へ」ボタン
					driver.findElement(By.className(C_BTN)).click();
					// 0.5秒待ち
					Thread.sleep(1000);
					continue;
				} else if (checkbox_count > 0) {
					driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(checkbox_count)).click();
					driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(checkbox_count)).click();
					driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(checkbox_count)).click();
					// 「次へ」ボタン
					driver.findElement(By.className(C_BTN)).click();
					// 0.5秒待ち
					Thread.sleep(1000);
					continue;
				} else {
					int select_count = driver.findElement(By.className("answer")).findElements(By.tagName("select"))
							.size();
					if (select_count > 0) {
						// 「セレクト」
						Select anser_select = new Select(
								driver.findElement(By.className("answer")).findElements(By.tagName("select")).get(0));
						anser_select.selectByIndex(2);
						// 「次へ」ボタン
						driver.findElement(By.className(C_BTN)).click();
						// 0.5秒待ち
						Thread.sleep(1000);
					} else {
						// 「次へ」ボタン
						driver.findElement(By.className(C_BTN_R)).click();
					}
					break;
				}

			}

			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}

	}

	/**
	 * =================================================================================================================
	 * 広告を閉じる
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 *
	 * @author kimC
	 *
	 */
	public static void ad_close(WebDriver driver) {
		try {
			driver.findElement(By.id("inter-close")).click();
			// 0.8秒待ち
			Thread.sleep(800);
		} catch (Exception e) {
		}
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
	public static void tab_close(WebDriver driver, String originalHandle, By by) {
		try {
			Boolean close_flag = Boolean.FALSE;
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHandle)) {
					driver.switchTo().window(handle);
					driver.close();
					close_flag = Boolean.TRUE;
				}
			}
			driver.switchTo().window(originalHandle);
			if (close_flag) {
				driver.findElement(by).click();
			}
		} catch (Exception e) {
		}

	}

}

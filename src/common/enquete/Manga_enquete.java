package common.enquete;

import static common.Common.*;
import static common.constant.HtmlConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * 共通：漫画アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Manga_enquete {

	/**
	 * =================================================================================================================
	 * 漫画アンケート
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 *
	 * @author kimC
	 *
	 */
	public static Boolean execute(WebDriver driver, AccountBean bean) throws Exception {
		String originalHandle = driver.getWindowHandle();

		// 0.5秒待ち
		Thread.sleep(1000);
		// 「進む」
		driver.findElement(By.xpath("//input[@alt='進む']")).click();
		// 「タブ閉じる」
		tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
		// 1秒待ち
		Thread.sleep(1500);
		// 「進む」
		driver.findElement(By.xpath("//input[@alt='漫画を選ぶ']")).click();
		// 「タブ閉じる」
		tab_close(driver, originalHandle, By.xpath("//input[@alt='漫画を選ぶ']"));
		for (int i = 0; i < 6; i++) {
			click_next(driver);
			// 1秒待ち
			Thread.sleep(500);
			// 「タブ閉じる」
			tab_close(driver, originalHandle, By.xpath("//input[@alt='進む']"));
		}
		// 1秒待ち
		Thread.sleep(1500);
		// 広告を閉じる
		ad_close(driver);
		// 「アンケートに進む」
		driver.findElement(By.xpath("//input[@alt='アンケートに進む']")).click();
		// 「タブ閉じる」
		tab_close(driver, originalHandle, By.xpath("//input[@alt='アンケートに進む']"));
		// 問題回答
		for (int i = 0; i < 10; i++) {
			// 0.5秒待ち
			Thread.sleep(500);
			// 問題内容
			String text03 = get_content(driver);
			if (str_matche(text03, "年齢をお知らせ")) {
				// 年齢
				driver.findElements(By.name("q1")).get(getAgeGroup(bean.getYear())).click();
			} else if (str_matche(text03, "性別をお知らせ")) {
				// 性別
				driver.findElements(By.name("q1")).get(getIndex(bean.getSex())).click();
			} else if (str_matche(text03, "職業をお知らせ")) {
				// 職業
				driver.findElements(By.name("q1")).get(int_random(7)).click();
			} else if (str_matche(text03, "お住まいをお知らせ")) {
				// お住まい
				Select element = new Select(driver.findElement(By.name("q1")));
				element.selectByIndex(getPrefList().indexOf(bean.getPref()));
			} else {
				int q_count = driver.findElements(By.name("q1")).size();
				if (q_count < 2) {
					// セレクト判断
					int select_count = driver.findElements(By.tagName(T_SELECT)).size();
					if (select_count > 0) {
						Select element = new Select(driver.findElement(By.name("q1")));
						element.selectByIndex(2);
					} else {
						break;
					}
				}
				driver.findElements(By.name("q1")).get(int_random(q_count)).click();
			}
			// 1秒待ち
			Thread.sleep(1500);
			// 「次へ」
			driver.findElement(By.xpath("//input[@value='次へ']")).click();
			// 「タブ閉じる」
			tab_close(driver, originalHandle, By.xpath("//input[@value='次へ']"));
		}
		// 1秒待ち
		Thread.sleep(1500);
		// 広告を閉じる
		ad_close(driver);
		// 「クリックしてポイントゲット」
		driver.findElement(By.xpath("//input[@alt='クリックしてポイントゲット']")).click();
		// 「タブ閉じる」
		tab_close(driver, originalHandle, By.xpath("//input[@alt='クリックしてポイントゲット']"));
		// 1秒待ち
		Thread.sleep(1500);
		// 「終了する」
		driver.findElement(By.id("again_bt")).findElement(By.tagName(T_A)).click();
		return Boolean.TRUE;
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

	/**
	 * =================================================================================================================
	 * アンケート内容を取得する
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 *
	 * @author kimC
	 *
	 */
	public static String get_content(WebDriver driver) {
		String text03 = "";
		try {
			text03 = driver.findElement(By.className("text03")).getText();
			return text03;
		} catch (Exception e) {
		}
		return text03;
	}

	/**
	 * =================================================================================================================
	 * 「進む」クリック
	 * =================================================================================================================
	 *
	 * @param WebDriver
	 *            driver
	 *
	 * @author kimC
	 *
	 */
	public static void click_next(WebDriver driver) {
		try {
			// 1秒待ち
			Thread.sleep(1500);
			// 広告を閉じる
			ad_close(driver);
			// 「漫画を読む」
			driver.findElement(By.xpath("//input[@alt='進む']")).click();
		} catch (Exception e) {
		}
	}
}

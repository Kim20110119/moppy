package common.enquete;

import static common.Common.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * =====================================================================================================================
 * 共通：エンタメリサーチ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Enta_Research {

	/** 「btn」 */
	private static final String C_BTN = "btn";
	/** 「btn_regular」 */
	private static final String C_BTN_R = "btn_regular";

	/**
	 * =================================================================================================================
	 * エンタメリサーチ
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
		// 0.5秒待ち
		Thread.sleep(500);
		// 「アンケートへ進む」
		driver.findElement(By.className("nextpage_navi")).findElement(By.tagName("a")).click();
		// 広告を閉じる
		ad_close(driver);
		// １問目
		WebElement ul_element_01= driver.findElement(By.id("ans_list")).findElement(By.tagName("ul"));
		ul_element_01.findElements(By.tagName("li")).get(0).findElement(By.tagName("input")).click();
		// 「次へ」
		driver.findElement(By.className("nextpage_navi")).findElement(By.tagName("a")).click();
		// 広告を閉じる
		ad_close(driver);
		// ２問目（性別）
		WebElement ul_element_02 = driver.findElement(By.id("ans_list")).findElement(By.tagName("ul"));
		ul_element_02.findElements(By.tagName("li")).get(0).findElement(By.tagName("input")).click();
		// 「次へ」
		driver.findElement(By.className("nextpage_navi")).findElement(By.tagName("a")).click();
		// 広告を閉じる
		ad_close(driver);
		// ３問目（年齢）
		WebElement ul_element_03 = driver.findElement(By.id("ans_list")).findElement(By.tagName("ul"));
		ul_element_03.findElements(By.tagName("li")).get(2).findElement(By.tagName("input")).click();
		// 「次へ」
		driver.findElement(By.className("nextpage_navi")).findElement(By.tagName("a")).click();
		// 広告を閉じる
		ad_close(driver);
		for(int i = 0; i < 7; i++){
			WebElement ul_element	 = driver.findElement(By.id("ans_list")).findElement(By.tagName("ul"));
			ul_element.findElements(By.tagName("li")).get(0).findElement(By.tagName("input")).click();
			int q_count = ul_element.findElements(By.tagName("li")).size();
			String type = ul_element.findElements(By.tagName("li")).get(0).findElement(By.tagName("input")).getAttribute("type");
			if(type == "redio"){
				ul_element.findElements(By.tagName("li")).get(int_random(q_count)).findElement(By.tagName("input")).click();
			}else{
				ul_element.findElements(By.tagName("li")).get(int_random(q_count)).findElement(By.tagName("input")).click();
				ul_element.findElements(By.tagName("li")).get(int_random(q_count)).findElement(By.tagName("input")).click();
				ul_element.findElements(By.tagName("li")).get(int_random(q_count)).findElement(By.tagName("input")).click();
			}
			// 「次へ」
			driver.findElement(By.className("nextpage_navi")).findElement(By.tagName("a")).click();
			// 広告を閉じる
			ad_close(driver);
		}
		// 広告を閉じる
		ad_close(driver);
		// 「次へ」
		driver.findElement(By.className("nextpage_navi")).findElement(By.tagName("a")).click();
		// 回答結果
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
			driver.findElement(By.xpath("//img[@src='../images/close.png']")).click();
			// 0.8秒待ち
			Thread.sleep(800);
		} catch (Exception e) {
		}
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
			next(driver);
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
	public static void next(WebDriver driver) {
		try {
			// 「次へ」ボタン
			driver.findElement(By.className("btn_regular")).click();
		} catch (Exception e) {
			driver.findElement(By.className(C_BTN)).click();
		}

	}

}

package common.enquete;

import static common.Common.*;
import static common.constant.CommonConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * =====================================================================================================================
 * 共通：Mini_Surveyアンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Mini_Survey_Enquete {

	/** 「btn」 */
	private static final String C_BTN = "btn";
	/** 「btn_regular」 */
	private static final String C_BTN_R = "btn_regular";

	/**
	 * =================================================================================================================
	 * Mini_Surveyアンケート
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
		// 2秒待ち
		Thread.sleep(2000);
		// 「次へ進む」ボタン
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// 0.5秒待ち
		Thread.sleep(500);
		// 「アンケート」
		for (int i = 0; i < 20; i++) {
			String question_label = driver.findElement(By.className("question-label")).getText();
			if(question_label.matches(S_SEX)){
				break;
			}
			// 1秒待ち
			Thread.sleep(1000);
			int q_r_count = driver.findElements(By.xpath("//input[@type='radio']")).size();
			int q_c_count = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
			if (q_r_count > 0) {
				driver.findElements(By.xpath("//input[@type='radio']")).get(int_random(q_r_count)).click();
			} else if (q_c_count > 0) {
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(q_c_count-1)).click();
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(q_c_count-1)).click();
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(q_c_count-1)).click();
			}
			// 1秒待ち
			Thread.sleep(1000);
			// 「次へ進む」ボタン
			driver.findElement(By.xpath("//button[@type='submit']")).click();
		}
		// 1秒待ち
		Thread.sleep(1000);
		// 「性別」
		driver.findElement(By.id("Q13_1")).click();
		// 1秒待ち
		Thread.sleep(1000);
		// 「次へ進む」ボタン
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// 1秒待ち
		Thread.sleep(1000);
		// 「年齢」
		driver.findElement(By.id("Q14_4")).click();
		// 1秒待ち
		Thread.sleep(1000);
		// 「次へ進む」ボタン
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// 1秒待ち
		Thread.sleep(1000);
		// 「未既婚」
		Select element = new Select(driver.findElement(By.name("Q15")));
		element.selectByValue("27");
		// 1秒待ち
		Thread.sleep(1000);
		// 「次へ進む」ボタン
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// 1秒待ち
		Thread.sleep(2000);
		// 「次へ進む」ボタン
		driver.findElement(By.xpath("//button[@type='submit']")).click();

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

}

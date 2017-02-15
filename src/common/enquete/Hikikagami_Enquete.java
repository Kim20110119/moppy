package common.enquete;

import static common.Common.*;
import static common.constant.CommonConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * =====================================================================================================================
 * 共通：Hikikagamiアンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Hikikagami_Enquete {

	/** 「panel-heading」 */
	private static final String C_P_H = "panel-heading";
	/** 「next_btn」 */
	private static final String C_N_B = "next_btn";

	/**
	 * =================================================================================================================
	 * Hikikagamiアンケート
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
		// 「次へ」
		driver.findElement(By.className(C_N_B)).click();

		// 「アンケート」
		for (int i = 0; i < 20; i++) {
			String text = driver.findElement(By.className(C_P_H)).findElement(By.tagName("p")).getText();
			// 「性別」
			if (text.matches(S_SEX)) {
				driver.findElements(By.xpath("//input[@type='radio']")).get(INT_0).click();
				driver.findElement(By.className(C_N_B)).click();
				continue;
			}
			// 「年齢」
			if (text.matches(S_AGE)) {
				driver.findElements(By.xpath("//input[@type='radio']")).get(INT_2).click();
				driver.findElement(By.className(C_N_B)).click();
				continue;
			}
			// 「職業」
			if (text.matches(S_JOB)) {
				driver.findElements(By.xpath("//input[@type='radio']")).get(INT_2).click();
				driver.findElement(By.className(C_N_B)).click();
				continue;
			}
			// 「住まい」
			if (text.matches(S_ADDRESS)) {
				Select element = new Select(driver.findElement(By.id("answers")));
				element.selectByIndex(27);
				driver.findElement(By.className(C_N_B)).click();
				continue;
			}
			int q_r_count = driver.findElements(By.xpath("//input[@type='radio']")).size();
			int q_c_count = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
			if (q_r_count > 0) {
				driver.findElements(By.xpath("//input[@type='radio']")).get(int_random(q_r_count)).click();
				driver.findElement(By.className(C_N_B)).click();
				continue;
			} else if (q_c_count > 0) {
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(q_c_count)).click();
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(q_c_count)).click();
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(q_c_count)).click();
				driver.findElement(By.className(C_N_B)).click();
				continue;
			} else {
				break;
			}

		}
		return Boolean.TRUE;
	}
}

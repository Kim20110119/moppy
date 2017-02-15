package common.enquete;

import static common.Common.*;
import static common.constant.HtmlConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import excute.bean.AccountBean;

/**
 * =====================================================================================================================
 * 共通(携帯)：Adsurveyアンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Adsurvey_Enquete {

	/** 「btn」 */
	private static final String C_BTN = "btn";
	/** 「btn_regular」 */
	private static final String C_BTN_R = "btn_regular";

	/**
	 * =================================================================================================================
	 * (携帯)Adsurveyアンケート
	 * =================================================================================================================
	 *
	 * @param WebDriver driver
	 * @return execute_flag
	 *
	 * @author kimC
	 *
	 */
	public static Boolean execute(WebDriver driver, AccountBean bean) throws Exception{
		// 0.5秒待ち
		Thread.sleep(500);
		if(check(driver)){
			return Boolean.TRUE;
		}
		// 「次へ」ボタン
		driver.findElement(By.xpath("//input[@value='次へ']")).click();
		// 0.5秒待ち
		Thread.sleep(500);
		// 「性別」
		driver.findElements(By.name("question_1")).get(getIndex(bean.getSex())).click();
		// 「次へ」ボタン
		driver.findElement(By.className(C_BTN)).click();
		// 0.5秒待ち
		Thread.sleep(500);
		// 「年齢」（30代）
		driver.findElements(By.name("question_2")).get(getAgeGroup(bean.getYear())).click();
		// 「次へ」ボタン
		driver.findElement(By.className(C_BTN)).click();
		// 0.5秒待ち
		Thread.sleep(500);
		// 「職業」（会社員）
		driver.findElements(By.name("question_3")).get(int_random(12)).click();
		// 「次へ」ボタン
		driver.findElement(By.className(C_BTN)).click();
		// 0.5秒待ち
		Thread.sleep(500);
		// 「住まい」（大阪）
		Select select = new Select(driver.findElement(By.name(QUE_4)));
		select.selectByIndex(getPrefList().indexOf(bean.getPref()) + 1);
		// 「次へ」ボタン
		driver.findElement(By.className(C_BTN)).click();
		// 0.5秒待ち
		Thread.sleep(500);
		// 「アンケート質問」
		for(int line = 1; line < 9; line++){
			//「ラジオボタン」
			int radio_count = driver.findElements(By.xpath("//input[@type='radio']")).size();
			// 「チェックボックス」
			int checkbox_count = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
			if(radio_count > 0){
				driver.findElements(By.xpath("//input[@type='radio']")).get(int_random(radio_count)).click();
				// 「次へ」ボタン
				driver.findElement(By.className(C_BTN)).click();
				// 0.5秒待ち
				Thread.sleep(1000);
				continue;
			}else if(checkbox_count > 0){
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(checkbox_count)).click();
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(checkbox_count)).click();
				driver.findElements(By.xpath("//input[@type='checkbox']")).get(int_random(checkbox_count)).click();
				// 「次へ」ボタン
				driver.findElement(By.className(C_BTN)).click();
				// 0.5秒待ち
				Thread.sleep(1000);
				continue;
			}else{
				// 「次へ」ボタン
				driver.findElement(By.className(C_BTN_R)).click();
				break;
			}

		}

		return Boolean.TRUE;
	}

	/**
	 * =================================================================================================================
	 * (携帯)Adsurveyアンケート
	 * =================================================================================================================
	 *
	 * @param WebDriver driver
	 * @return execute_flag
	 *
	 * @author kimC
	 *
	 */
	public static Boolean check(WebDriver driver) throws Exception{
		Boolean flag = Boolean.FALSE;
		try{
			if(driver.findElements(By.className("attention_txt")).size() > 0){
				flag =  Boolean.TRUE;
			}
		}catch (Exception e){
		}
		return flag;
	}

}

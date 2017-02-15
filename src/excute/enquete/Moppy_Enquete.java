package excute.enquete;

import static common.constant.CommonConstants.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.openqa.selenium.By;

import common.enquete.Adsurvey_Enquete;
import common.enquete.Column_Enquete;
import common.enquete.Mini_Survey_Enquete;
import excute.Pc_Moppy;

/**
 * =====================================================================================================================
 * チャンスイット：アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Enquete extends Pc_Moppy {

	/** 「ga-answerBtn」 */
	private static final String C_G_A = "ga-answerBtn";
	/** 「アンケートURL」 */
	String enquete_url;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;
	/** 「アンケート件数」 */
	int enquete_count = 0;
	/** 「アンケートURL」 */
	int enquete_index = 0;

	/**
	 * コンストラクタ
	 */
	public Moppy_Enquete() {
		// 「アンケート」
		driver.get(PC_ENQUETE_URL);
	}

	/**
	 * =================================================================================================================
	 * アンケートメイン処理
	 * =================================================================================================================
	 *
	 * @return int point_couont 獲得済みポイント
	 *
	 * @author kimC
	 *
	 */
	public Integer execute() {
		// アンケートカウントを取得する
		int enquete_count = driver.findElements(By.className(C_G_A)).size();
		for (int i = 0; i < enquete_count; i++) {
			// 「アンケートURL」取得する
			enquete_url = driver.findElements(By.className(C_G_A)).get(enquete_index).getAttribute(A_HREF);
			driver.get(enquete_url);
			String current_url = driver.getCurrentUrl();
			if (current_url.matches(COLUMN_URL)) {
				column_start();
			} else if (current_url.matches(MINI_SURVEY_URL)) {
				mini_start();
			} else if (current_url.matches(ADSURVEY_URL)) {
				ad_start();
			}else{
				enquete_index++;
			}
			// 「アンケート」
			driver.get(PC_ENQUETE_URL);
		}

		driver.quit();
		return point_count;

	}

	/**
	 * =================================================================================================================
	 * MINIアンケートスタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void mini_start() {
		try {
			Mini_Survey_Enquete.execute(driver);
			point_count += 5;
		} catch (Exception e) {
			System.out.println("【エラー】：MINIアンケートスタート失敗");
		}
	}

	/**
	 * =================================================================================================================
	 * ADアンケートスタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void ad_start() {
		try {
			Adsurvey_Enquete.execute(driver);
			point_count += 5;
		} catch (Exception e) {
			System.out.println("【エラー】：ADアンケートスタート失敗");
		}
	}

	/**
	 * =================================================================================================================
	 * COLUMNアンケートスタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void column_start() {
		try {
			Column_Enquete.execute(driver);
			point_count += 5;
		} catch (Exception e) {
			System.out.println("【エラー】：COLUMNアンケートスタート失敗");
		}
	}
}

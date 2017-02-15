package excute.research;

import static common.Common.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import excute.Pc_Moppy;

/**
 * =====================================================================================================================
 * モッピー：ポイントリサーチ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Research extends Pc_Moppy {
	/** 「pointResearch__box__btn」 */
	private static final String C_P_B_B = "pointResearch__box__btn";
	/** 「ui-button」 */
	private static final String C_U_B = "ui-button";
	/** 「ui-label-radio」 */
	private static final String C_U_L_R = "ui-label-radio";
	/** 「ui-label-checkbox」 */
	private static final String C_U_L_C = "ui-label-checkbox";
	/** 「ui-select」 */
	private static final String C_U_S = "ui-select";

	/** 「ポイントリサーチURL」 */
	String election_url;
	/** 「獲得ポイント」 */
	int point_count = 0;
	/** 「再スタートフラグ」 */
	Boolean restart_flag = Boolean.FALSE;
	/** 「アンケート件数」 */
	int enquete_count = 0;

	/**
	 * コンストラクタ
	 */
	public Moppy_Research() {
		// 「ポイントリサーチ」
		driver.get(PC_POINT_RESEARCH_URL);
	}

	/**
	 * =================================================================================================================
	 * ポイントリサーチ
	 * =================================================================================================================
	 *
	 * @return int point_couont 獲得済みポイント
	 *
	 * @author kimC
	 *
	 */
	public Integer execute() {
		try {
			// 「モッピー」アンケート一覧画面へ遷移する
			int enquete_count = getSize(getByClass(C_P_B_B));
			for (int i = 0; i < enquete_count; i++) {
				start();
				// 「ポイントリサーチ」
				driver.get(PC_POINT_RESEARCH_URL);
			}
			driver.quit();
			return point_count;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：ポイントリサーチ失敗");
			return point_count;
		}
	}

	/**
	 * =================================================================================================================
	 * 投票スタート
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void start() {
		try {
			String enquet_url = driver.findElements(By.className(C_P_B_B)).get(0).getAttribute(A_HREF);
			driver.get(enquet_url);
			// 「アンケートに進む」
			click(getByClass(C_U_B));
			// 「回答開始」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「性別」
			clickByIndex(getByClass(C_U_L_R), 0);
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「年齢」
			clickByIndex(getByClass(C_U_L_R), 2);
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「未既婚」
			clickByIndex(getByClass(C_U_L_R), 1);
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「住所」
			selectByIndex(getByClass(C_U_S), 6);
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 1秒待ち
			sleep(1500);
			// 「職業」
			clickByIndex(getByClass(C_U_L_R), 2);
			// 1秒待ち
			sleep(1500);
			// 「次へ」
			click(getByClass(C_U_B));
			// 「アンケート回答」
			for (int j = 0; j < 20; j++) {
				// 1秒待ち
				sleep(1500);
				int q_r_count = getSize(getByClass(C_U_L_R));
				int q_c_count = getSize(getByClass(C_U_L_C));
				int q_s_count = getSize(getByClass(C_U_S));
				if (q_r_count > 0) {
					clickByIndex(getByClass(C_U_L_R), int_random(q_r_count));
				} else if (q_c_count > 0) {
					clickByIndex(getByClass(C_U_L_C), int_random(q_c_count));
					clickByIndex(getByClass(C_U_L_C), int_random(q_c_count));
					clickByIndex(getByClass(C_U_L_C), int_random(q_c_count));
				} else if (q_s_count > 0) {
					selectByIndex(getByClass(C_U_S), int_random(3));
				} else {
					break;
				}
				// 1秒待ち
				sleep(1500);
				// 「次へ」
				click(getByClass(C_U_B));
			}
			// 1秒待ち
			sleep(1500);
			// 広告を閉じる
			ad_close(driver);
			// 「ポイント獲得」
			click(getByXpath(T_INPUT, A_VALUE, "ポイント獲得"));
			// 1秒待ち
			sleep(1500);
			ad_close(driver);
			// 「閉じる」
			driver.findElement(By.partialLinkText("閉じる"));
			point_count += 1;
		} catch (Exception e) {
			System.out.println("【エラー】：ポイントリサーチ失敗");
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
	public static  void ad_close(WebDriver driver) {
		try {
			driver.findElement(By.partialLinkText("閉じる")).click();
			// 0.8秒待ち
			Thread.sleep(1500);
		} catch (Exception e) {
		}
	}
}

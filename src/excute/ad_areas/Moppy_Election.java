package excute.ad_areas;

import static common.Common.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import excute.Pc_Moppy;



/**
 * =====================================================================================================================
 * モッピー：クマクマ総選挙
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Moppy_Election extends Pc_Moppy {
	/** 「daily-points」 */
	private static final String C_D_P = "daily-points";
	/** 「クマクマ総選挙URL」 */
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
	public Moppy_Election() {
		// 「CMくじ」
		driver.get(PC_CM_URL);
	}

	/**
	 * =================================================================================================================
	 * クマクマ総選挙
	 * =================================================================================================================
	 *
	 * @return int point_couont 獲得済みポイント
	 *
	 * @author kimC
	 *
	 */
	public Integer execute() {
		try {
			// 「クマクマ総選挙URL」取得する
			election_url = driver.findElement(By.className(C_D_P)).findElements(By.tagName(T_A)).get(5)
					.getAttribute(A_HREF);
			if (StringUtils.isNoneEmpty(election_url)) {
				// 「クマクマ総選挙画面」
				driver.get(election_url);
				// 「さっそく投票を始める！」
				click(getByClass("start__button"));
				// アンケート件数
				enquete_count = driver.findElement(By.className("select__list")).findElements(By.tagName(T_A)).size();
				if (enquete_count < 1) {
					driver.quit();
					return point_count;
				}
				// 「投票画面」
				driver.findElement(By.className("select__list")).findElements(By.tagName(T_A)).get(0).click();
				// 投票処理をする
				for (int i = 0; i < 500; i++) {
					start();
					if (restart_flag) {
						// 「クマクマ総選挙画面」
						driver.get(election_url);
						// 「アンケート開始」
						click(getByClass("start__button"));
						// アンケート件数
						enquete_count = driver.findElement(By.className("select__list")).findElements(By.tagName(T_A)).size();
						if (enquete_count < 1) {
							driver.quit();
							return point_count;
						}else{
							// 「アンケート画面」
							driver.findElement(By.className("select__list")).findElements(By.tagName(T_A)).get(0).click();
						}
					}
					// 獲得ポイントカウント
					point_count += 1;
				}
			} else {
				System.out.println("【エラー】：クマクマ総選挙URL取得失敗");
			}
			driver.quit();
			return point_count;
		} catch (Exception e) {
			driver.quit();
			System.out.println("【エラー】：クマクマ総選挙失敗");
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
			// 「投票選択肢カウント」
			int choice_count = getSize(getByXpath("label", "class", "radio"));
			// 「投票選択肢」クリック
			clickByIndex(getByXpath("label", "class", "radio"), int_random(choice_count));
			// 1秒待ち
			sleep(1000);
			// 「次へ」ボタン
			driver.findElement(By.className("button--answer")).click();
			// 1秒待ち
			sleep(1000);
			// 「次の投票へ」
			driver.findElement(By.className("button__box")).findElements(By.tagName(T_A)).get(0).click();
			// 1秒待ち
			sleep(1000);
		} catch (Exception e) {
			System.out.println("【エラー】：選挙スタート失敗");
			System.out.println("【エラー】：クマクマ総選挙遷移再スタート");
			point_count -= 1;
			// 「再スタートフラグ」
			restart_flag = Boolean.TRUE;
		}
	}

}

package excute.ad_areas;

import static common.Common.*;
import static common.constant.HtmlConstants.*;
import static common.constant.MoppyConstants.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import excute.Pc_Moppy;
import excute.bean.AccountBean;



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
	/** 「開始Index」 */
	int start = 0;
	/** 「終了Index」 */
	int end = 200;
	/** 「アカウント情報」 */
	AccountBean bean = new AccountBean();

	/**
	 * コンストラクタ
	 */
	public Moppy_Election() {
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
	public Integer execute(AccountBean pBean, Boolean loginFlag) {
		try {
			this.bean = pBean;
			if(loginFlag){
				// モッピー：ログイン画面
				driver.get(PC_LOGIN_URL);
				// モッピー：ログインメールアドレス
				sendkeysByStr(getByName(V_MAIL), bean.getMail());
				// モッピー：ログインパスワード
				sendkeysByStr(getByName(V_PASS), bean.getPassword());
				// モッピー：ログインボタン
				click(getByXpath(T_BUTTON, A_TYPE, V_SUBMIT));
			}
			// 「CMくじ」
			driver.get(PC_CM_URL);
			// 「クマクマ総選挙URL」取得する
			election_url = driver.findElement(By.className(C_D_P)).findElements(By.tagName(T_A)).get(2)
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
				for (int i = start; i < end; i++) {
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
			// 「再スタートフラグ」
			restart_flag = Boolean.FALSE;
			// 「投票選択肢」クリック
			this.select();
			// 「次へ」ボタン
			this.next();
			// 「次の投票へ」
			this.next_start();
		} catch (Exception e) {
		}
	}

	/**
	 * =================================================================================================================
	 * 「投票選択肢」クリック処理
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void select() {
		try {
			// 1秒待ち
			sleep(1000);
			// 0.5秒待ち
			wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath("label", "class", "radio")));
			// 「投票選択肢カウント」
			int choice_count = getSize(getByXpath("label", "class", "radio"));
			// 「投票選択肢」クリック
			clickByIndex(getByXpath("label", "class", "radio"), int_random(choice_count));
		} catch (Exception e) {
			try {
				// 「投票選択肢」クリック
				clickByIndex(getByXpath("label", "class", "radio"), int_random(getSize(getByXpath("label", "class", "radio"))));
			} catch (Exception r_e){
				// 「再スタートフラグ」
				restart_flag = Boolean.TRUE;
			}
		}
	}

	/**
	 * =================================================================================================================
	 * 「次へ」ボタンクリック処理
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void next() {
		try {
			// 1秒待ち
			sleep(1000);
			// 「次へ」ボタン
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("button--answer"))));
			driver.findElement(By.className("button--answer")).click();
		} catch (Exception e) {
			super.scroll(0, 100);
			try {
				driver.findElement(By.className("button--answer")).click();
			} catch (Exception r_e){
				// 「再スタートフラグ」
				restart_flag = Boolean.TRUE;
			}
		}
	}

	/**
	 * =================================================================================================================
	 * 「次の投票へ」ボタンクリック処理
	 * =================================================================================================================
	 *
	 * @author kimC
	 *
	 */
	public void next_start() {
		try {
			// 1秒待ち
			sleep(1000);
			// 「次の投票へ」ボタン
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("button__box")).findElements(By.tagName(T_A)).get(0)));
			driver.findElement(By.className("button__box")).findElements(By.tagName(T_A)).get(0).click();
		} catch (Exception e) {
			super.scroll(0, 100);
			try {
				// 1秒待ち
				sleep(1000);
				driver.findElement(By.className("button__box")).findElements(By.tagName(T_A)).get(0).click();
			} catch (Exception r_e){
				// 「再スタートフラグ」
				restart_flag = Boolean.TRUE;
			}
		}
	}

}

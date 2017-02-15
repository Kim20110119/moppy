package excute;

import excute.click_coin.Moppy_Click_Coin;
/**
 * =====================================================================================================================
 * 【モッピー】：毎日バナークリック
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_ClickCoinMain {
	public static void main(String[] args) {
		// モッピー：毎日バナークリック
		Moppy_Click_Coin mcc = new Moppy_Click_Coin();
		int point = mcc.execute();
		System.out.println("毎日バナークリック終了。獲得済みポイント"+point);
	}
}
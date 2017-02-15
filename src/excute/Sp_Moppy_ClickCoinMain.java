package excute;

import excute.sp.Sp_Moppy_Click_Coin;

public class Sp_Moppy_ClickCoinMain {

	public static void main(String[] args) {
		// モッピー：クマクマ総選挙
		Sp_Moppy_Click_Coin mcc = new Sp_Moppy_Click_Coin();
		int point = mcc.execute();
		System.out.println("毎日バナークリック終了。獲得済みポイント"+point);
	}

}
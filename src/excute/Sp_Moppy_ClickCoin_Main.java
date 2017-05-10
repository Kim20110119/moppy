package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Click_Coin;

public class Sp_Moppy_ClickCoin_Main {

	public static void main(String[] args) {
		// モッピー：クマクマ総選挙
		Sp_Moppy_Click_Coin mcc = new Sp_Moppy_Click_Coin();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			mcc.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：（携帯版）毎日バナークリック終了。");
	}

}
package excute;

import excute.bean.AccountBean;
import excute.click_coin.Moppy_Click_Coin;
import excute.excel.Account;
/**
 * =====================================================================================================================
 * 【モッピー】：毎日バナークリック
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_ClickCoin_Main {
	public static void main(String[] args) {
		// モッピー：毎日バナークリック
		Moppy_Click_Coin mcc = new Moppy_Click_Coin();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			mcc.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：毎日クリック終了。");
	}
}
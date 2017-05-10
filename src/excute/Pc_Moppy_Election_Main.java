package excute;

import excute.ad_areas.Moppy_Election;
import excute.bean.AccountBean;
import excute.excel.Account;

/**
 * =====================================================================================================================
 * 【モッピー】：クマクマ総選挙
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Election_Main {

	public static void main(String[] args) {
		// モッピー：クマクマ総選挙
		Moppy_Election election = new Moppy_Election();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			election.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：クマクマ総選挙終了。獲得済みポイント");
	}

}
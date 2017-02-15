package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Election;

/**
 * =====================================================================================================================
 * モッピー：クマクマ総選挙(携帯)
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Election_Main {

	public static void main(String[] args) {
		Sp_Moppy_Election election = new Sp_Moppy_Election();
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			election.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：（携帯版）クマクマ総選挙終了。");
	}

}
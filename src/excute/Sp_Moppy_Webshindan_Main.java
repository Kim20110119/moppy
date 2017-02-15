package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Shindan;

/**
 * =====================================================================================================================
 * モッピー：WEB診断(携帯)
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Webshindan_Main {

	public static void main(String[] args) {
		Sp_Moppy_Shindan shindan = new Sp_Moppy_Shindan();
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			shindan.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：（携帯版）WEB診断終了。");
	}

}
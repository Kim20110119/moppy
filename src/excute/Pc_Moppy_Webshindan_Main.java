package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.shindan.Moppy_Shindan;

/**
 * =====================================================================================================================
 * モッピー：WEB診断
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Webshindan_Main {
	// モッピー：WEB診断
	public static void main(String[] args) {
		Moppy_Shindan moppy_Shindan = new Moppy_Shindan();
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			moppy_Shindan.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：WEB診断終了。");
	}

}
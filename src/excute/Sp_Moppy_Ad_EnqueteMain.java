package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Ad_Enquete;

/**
 * =====================================================================================================================
 * 【モッピー】：漫画アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Ad_EnqueteMain {

	public static void main(String[] args) {
		// モッピー：漫画アンケート
		Sp_Moppy_Ad_Enquete enquete = new Sp_Moppy_Ad_Enquete();
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			enquete.execute(bean, Boolean.TRUE);
		}
		System.out.println("漫画アンケート終了。獲得済みポイント");
	}

}
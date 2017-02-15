package excute;

import excute.ad_areas.Moppy_Ad_Enquete;
import excute.bean.AccountBean;
import excute.excel.Account;

/**
 * =====================================================================================================================
 * 【モッピー】：漫画アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Ad_EnqueteMain {

	public static void main(String[] args) {
		// モッピー：漫画アンケート
		Moppy_Ad_Enquete enquete = new Moppy_Ad_Enquete();
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			enquete.execute(bean, Boolean.TRUE);
		}
		System.out.println("漫画アンケート終了。獲得済みポイント");
	}

}
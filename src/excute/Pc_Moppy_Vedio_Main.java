package excute;

import excute.ad_areas.Moppy_Vedio;
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
public class Pc_Moppy_Vedio_Main {

	public static void main(String[] args) {
		// モッピー：漫画アンケート
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			Moppy_Vedio enquete = new Moppy_Vedio();
			enquete.execute(bean, Boolean.TRUE);
		}
		System.out.println("動画完了");
	}

}
package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Vedio;

/**
 * =====================================================================================================================
 * 【モッピー】：漫画アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Vedio_Main {

	public static void main(String[] args) {
		// モッピー：漫画アンケート
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			Sp_Moppy_Vedio vedio = new Sp_Moppy_Vedio();
			vedio.execute(bean, Boolean.TRUE);
		}
		System.out.println("動画完了");
	}

}
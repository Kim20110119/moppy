package excute;

import excute.ad_areas.Moppy_Reados;
import excute.bean.AccountBean;
import excute.excel.Account;

/**
 * =====================================================================================================================
 * 【モッピー】：クマクマ調査団
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Reados_Main {
	// 【モッピー】：クマクマ調査団
	public static void main(String[] args) {
		Moppy_Reados reados = new Moppy_Reados();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			reados.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：クマクマ調査団終了。");
	}
}

package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Reados;

/**
 * =====================================================================================================================
 * モッピー：クマクマ調査団(携帯)
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Reados_Main {

	public static void main(String[] args) {
		Sp_Moppy_Reados reados = new Sp_Moppy_Reados();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			reados.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：（携帯版）クマクマ調査団終了。");
	}

}
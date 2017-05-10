package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.research.Moppy_Research;
/**
 * =====================================================================================================================
 * モッピー：ポイントリサーチメインクラス
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Research_Main {

	public static void main(String[] args) {
		// モッピー：リサーチアンケート
		Moppy_Research research = new Moppy_Research();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			for(int i = 0; i < 3; i++){
				research.execute(bean, Boolean.TRUE);
			}

		}
		System.out.println("【モッピー】：リサーチアンケート終了。");
	}

}
package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.sp.Sp_Moppy_Game;

/**
 * =====================================================================================================================
 * モッピー：ガチャ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Game_Main {

	public static void main(String[] args) {
		// モッピー：アンケート
		Sp_Moppy_Game game = new Sp_Moppy_Game();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			game.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：ガチャ終了。");
	}

}
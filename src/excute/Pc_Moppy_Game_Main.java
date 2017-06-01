package excute;

import excute.bean.AccountBean;
import excute.excel.Account;
import excute.game.Moppy_Game;

/**
 * =====================================================================================================================
 * モッピー：ガチャ
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Game_Main {

	public static void main(String[] args) {
		// モッピー：アンケート
		Moppy_Game game = new Moppy_Game();
		Account account = new Account();
		for(AccountBean bean : account.execute(args[0])){
			game.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：ガチャ終了。");
	}

}
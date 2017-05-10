package excute;

import excute.confirm.Moppy_Confirm;
import excute.excel.Account;

/**
 * =====================================================================================================================
 * 【モッピー】：獲得済みポイント抽出
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Confirm_Main {

	public static void main(String[] args) {
		// モッピー：獲得済みポイント抽出
		Moppy_Confirm confirm = new Moppy_Confirm();
		Account account = new Account();
		confirm.execute(account.execute(args[0]));
		System.out.println("【モッピー】：獲得済みポイント抽出処理完了");
	}

}
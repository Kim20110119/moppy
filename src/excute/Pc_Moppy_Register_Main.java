package excute;

import excute.excel.Register;
import excute.register.Moppy_Register;
/**
 * =====================================================================================================================
 * 【モッピー】：新規登録
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Register_Main {
	public static void main(String[] args) {
		// モッピー：毎日バナークリック
		Moppy_Register register = new Moppy_Register();
		Register account = new Register();
		register.execute(account.execute());
		System.out.println("モッピー新規登録完了");
	}
}
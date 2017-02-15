package excute;

import excute.enquete.Moppy_Enquete;

/**
 * =====================================================================================================================
 * モッピー：アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_EnqueteMain {

	public static void main(String[] args) {
		// モッピー：アンケート
		Moppy_Enquete enquete = new Moppy_Enquete();
		int point = enquete.execute();
		System.out.println("【モッピー】アンケート終了。獲得済みポイント"+point);
	}

}
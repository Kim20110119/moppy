package excute;

import excute.research.Moppy_Research;
/**
 * =====================================================================================================================
 * モッピー：ポイントリサーチメインクラス
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_ResearchMain {

	public static void main(String[] args) {
		// モッピー：リサーチアンケート
		Moppy_Research research = new Moppy_Research();
		int point = research.execute();
		System.out.println("【モッピー】：リサーチアンケート終了。獲得済みポイント"+point);
	}

}
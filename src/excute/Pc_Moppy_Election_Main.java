package excute;

import excute.ad_areas.Moppy_Election;

/**
 * =====================================================================================================================
 * 【モッピー】：クマクマ総選挙
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Election_Main {

	public static void main(String[] args) {
		// モッピー：漫画アンケート
		Moppy_Election election = new Moppy_Election();
		int point = election.execute();
		System.out.println("クマクマ総選挙。獲得済みポイント"+point);
	}

}
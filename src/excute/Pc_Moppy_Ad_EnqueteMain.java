package excute;

import excute.ad_areas.Moppy_Ad_Enquete;

/**
 * =====================================================================================================================
 * 【モッピー】：漫画アンケート
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Ad_EnqueteMain {

	public static void main(String[] args) {
		// モッピー：漫画アンケート
		Moppy_Ad_Enquete enquete = new Moppy_Ad_Enquete();
		int point = enquete.execute();
		System.out.println("漫画アンケート終了。獲得済みポイント"+point);
	}

}
package excute;

import excute.shindan.Moppy_Shindan;

/**
 * =====================================================================================================================
 * モッピー：WEB診断
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Webshindan_Main {

	public static void main(String[] args) {
		Moppy_Shindan moppy_Shindan = new Moppy_Shindan();
		int reados_point = moppy_Shindan.execute();
		System.out.println(reados_point);
	}

}
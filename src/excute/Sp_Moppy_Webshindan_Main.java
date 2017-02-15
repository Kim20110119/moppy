package excute;

import excute.sp.Sp_Moppy_Shindan;

/**
 * =====================================================================================================================
 * モッピー：WEB診断(携帯)
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Webshindan_Main {

	public static void main(String[] args) {
		Sp_Moppy_Shindan shindan = new Sp_Moppy_Shindan();
		int reados_point = shindan.execute();
		System.out.println(reados_point);
	}

}
package excute;

import excute.sp.Sp_Moppy_Reados;

/**
 * =====================================================================================================================
 * モッピー：WEB診断(携帯)
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Sp_Moppy_Reados_Main {

	public static void main(String[] args) {
		Sp_Moppy_Reados reados = new Sp_Moppy_Reados();
		int reados_point = reados.execute();
		System.out.println(reados_point);
	}

}
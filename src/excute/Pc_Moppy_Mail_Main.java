package excute;

import excute.mail.Moppy_Mail;
/**
 * =====================================================================================================================
 * 【モッピー】：メール確認
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_Mail_Main {
	public static void main(String[] args) {
		// モッピー：メール確認
		Moppy_Mail m_mail = new Moppy_Mail(args[0], args[1], args[2]);
		m_mail.execute();
		System.out.println("【モッピー】：メール確認終了。");
	}
}
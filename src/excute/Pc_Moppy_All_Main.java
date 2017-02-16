package excute;

import excute.ad_areas.Moppy_Election;
import excute.ad_areas.Moppy_Reados;
import excute.bean.AccountBean;
import excute.click_coin.Moppy_Click_Coin;
import excute.excel.Account;
import excute.research.Moppy_Research;
import excute.shindan.Moppy_Shindan;
import excute.sp.Sp_Moppy_Election;
import excute.sp.Sp_Moppy_Reados;
import excute.sp.Sp_Moppy_Shindan;

/**
 * =====================================================================================================================
 * 【モッピー】：自動化
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Moppy_All_Main {
	// 【モッピー】：自動化
	public static void main(String[] args) {
		Account account = new Account();
		for(AccountBean bean : account.execute()){
			// 「クマクマ調査団」
			Moppy_Reados reados = new Moppy_Reados();
			reados.execute(bean, Boolean.TRUE);
			// 「リサーチアンケート」
			Moppy_Research research = new Moppy_Research();
			research.execute(bean, Boolean.TRUE);
			// 「毎日クリック
			Moppy_Click_Coin mcc = new Moppy_Click_Coin();
			mcc.execute(bean, Boolean.TRUE);
			// 「WEB診断」
			Moppy_Shindan shindan = new Moppy_Shindan();
			shindan.execute(bean, Boolean.TRUE);
			// 「クマクマ総選挙」
			Moppy_Election election = new Moppy_Election();
			election.execute(bean, Boolean.TRUE);
			// 「WEB診断」(携帯)
			Sp_Moppy_Shindan sp_shindan = new Sp_Moppy_Shindan();
			sp_shindan.execute(bean, Boolean.TRUE);
			// 「クマクマ調査団」(携帯)
			Sp_Moppy_Reados sp_reados = new Sp_Moppy_Reados();
			sp_reados.execute(bean, Boolean.TRUE);
			// 「クマクマ総選挙」(携帯)
			Sp_Moppy_Election sp_election = new Sp_Moppy_Election();
			sp_election.execute(bean, Boolean.TRUE);
		}
		System.out.println("【モッピー】：自動化終了。");
	}
}

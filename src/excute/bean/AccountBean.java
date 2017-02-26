package excute.bean;

/**
 * =====================================================================================================================
 * 【モッピー】登録アカウント用Bean
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class AccountBean {
	//==================================================================================================================
	// 登録アカウント
	//==================================================================================================================
	/** メールアドレス */
	private String mail;
	/** ニックネーム */
	private String nick;
	/** パスワード */
	private String password;
	/** 性別 */
	private String sex;
	/** 年 */
	private String year;
	/** 月 */
	private String month;
	/** 都道府県 */
	private String pref;
	/** 未既婚 */
	private String married;
	/** 秘密の質問 */
	private String codeid;
	/** 秘密の回答 */
	private String codeword;
	/** 獲得ポイント */
	private String point;

	/**
	 * @return メールアドレス
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param メールアドレスをセットする
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return ニックネーム
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * @param ニックネームをセットする
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param パスワードをセットする
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return 性別
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param 性別をセットする
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return 年
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param 年をセットする
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return 月
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param 月をセットする
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return 都道府県
	 */
	public String getPref() {
		return pref;
	}
	/**
	 * @param 都道府県をセットする
	 */
	public void setPref(String pref) {
		this.pref = pref;
	}
	/**
	 * @return 未既婚
	 */
	public String getMarried() {
		return married;
	}
	/**
	 * @param 未既婚をセットする
	 */
	public void setMarried(String married) {
		this.married = married;
	}
	/**
	 * @return 秘密の質問
	 */
	public String getCodeid() {
		return codeid;
	}
	/**
	 * @param 秘密の質問をセットする
	 */
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	/**
	 * @return 秘密の回答
	 */
	public String getCodeword() {
		return codeword;
	}
	/**
	 * @param 秘密の回答をセットする
	 */
	public void setCodeword(String codeword) {
		this.codeword = codeword;
	}
	/**
	 * @return 獲得ポイント
	 */
	public String getPoint() {
		return point;
	}
	/**
	 * @param 獲得ポイントをセットする
	 */
	public void setPoint(String point) {
		this.point = point;
	}

}

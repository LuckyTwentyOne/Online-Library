package ua.nure.butov.summaryTask4.model;

import ua.nure.butov.summaryTask4.annotation.Column;

/**
 * Relation entity between account and unique code.
 * 
 * @author V.Butov
 *
 */
public class AccountRegistration {

	@Column("id_account")
	private long idAccount;
	private String code;

	public long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(long idAccount) {
		this.idAccount = idAccount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

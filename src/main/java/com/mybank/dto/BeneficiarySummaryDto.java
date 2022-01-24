package com.mybank.dto;


import javax.validation.constraints.NotEmpty;

public class BeneficiarySummaryDto {
	
	@NotEmpty(message="BeneficiaryAaccountNnumber should not be empty")
	private String beneficiaryAaccountNnumber;
	@NotEmpty(message="IFSC code should not be empty")
	private String ifsc;
	@NotEmpty(message="Short name should not be empty")
	private String shortName;
	@NotEmpty(message="Bank name should not be empty")
	private String bankName;
	@NotEmpty(message="Account number should not be empty")
	private String accountNumber;
	
	/**
	 * @return the beneficiaryAaccountNnumber
	 */
	public String getBeneficiaryAaccountNnumber() {
		return beneficiaryAaccountNnumber;
	}
	/**
	 * @param beneficiaryAaccountNnumber the beneficiaryAaccountNnumber to set
	 */
	public void setBeneficiaryAaccountNnumber(String beneficiaryAaccountNnumber) {
		this.beneficiaryAaccountNnumber = beneficiaryAaccountNnumber;
	}
	/**
	 * @return the ifsc
	 */
	public String getIfsc() {
		return ifsc;
	}
	/**
	 * @param ifsc the ifsc to set
	 */
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}

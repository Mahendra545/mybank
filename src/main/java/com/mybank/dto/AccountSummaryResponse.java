package com.mybank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountSummaryResponse {

	
	private String accountHolderName;
	private String accountNumber;
	private String accountType;
	private Double availableBalance;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date creationDate;
	/**
	 * @return the accountHolderName
	 */
	public String getAccountHolderName() {
		return accountHolderName;
	}
	/**
	 * @param accountHolderName the accountHolderName to set
	 */
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
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
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the availableBalance
	 */
	public Double getAvailableBalance() {
		return availableBalance;
	}
	/**
	 * @param availableBalance the availableBalance to set
	 */
	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}

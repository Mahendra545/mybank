package com.mybank.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class TransferRequest {

	@NotEmpty(message = "Please enter primaryAccountNumber")
	private String accountNumber;

	@NotEmpty(message = "Please enter beneficiaryAccountNumber")	
	private String beneficiaryAccountNumber;

	@DecimalMin("1")
	@NotNull(message = "Please enter transferAmount")
	private double amount;

	private String comments;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	

}

package com.mybank.dto;

import java.util.List;

import com.mybank.entity.Transactions;

public class TansactionsResponse {
	private String accountNumber;
	private double availableBalance;
	private List<Transactions> transectionList;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public List<Transactions> getTransectionList() {
		return transectionList;
	}
	public void setTransectionList(List<Transactions> transectionList) {
		this.transectionList = transectionList;
	}
	
	
}

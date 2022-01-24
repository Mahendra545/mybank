package com.mybank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
@Column(name = "beneficiary_account_number")
private String beneficiaryAccountNumber;
@Column(name = "ifsc")
private String ifsc;
@Column(name = "short_name")
private String shortName;
@Column(name = "bank_name")
private String bankName;
@Column(name = "account_number")
private String accountNumber;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getBeneficiaryAccountNumber() {
	return beneficiaryAccountNumber;
}
public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
	this.beneficiaryAccountNumber = beneficiaryAccountNumber;
}
public String getIfsc() {
	return ifsc;
}
public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
}
public String getShortName() {
	return shortName;
}
public void setShortName(String shortName) {
	this.shortName = shortName;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

}

package com.mybank.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transactions")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Integer id;

	@Column(name = "destination_account_number")
	private String destinationAccountNumber;

	@Column(name = "primary_account_number")
	private String primaryAccountNumber;

	@Column(name = "amount")
	private double amount;

	@Column(name = "transaction_date")	
	@CreationTimestamp
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
	private Timestamp transactionDate;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "comments")
	private String comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}
	@JsonIgnore
	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}

package com.mybank.dto;

import javax.validation.constraints.NotEmpty;


public class AuthenticationRequest {

	@NotEmpty
	private String customerId;
	
	@NotEmpty
	private String password;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
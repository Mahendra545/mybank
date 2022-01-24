package com.mybank.service;

import com.mybank.dto.AccountSummaryResponse;
import com.mybank.dto.TansactionsResponse;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;

public interface AccountSummaryService {

	TansactionsResponse getTansactions(String accountNumber) throws DataNotFoundException;

	 AccountSummaryResponse getAccountSummary(String accountNumber)
			throws DataNotFoundException, BeneficiaryException;
}

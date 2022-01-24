package com.mybank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.dto.AccountSummaryResponse;
import com.mybank.dto.TansactionsResponse;
import com.mybank.entity.Account;
import com.mybank.entity.Transactions;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.TransactionsRepository;
import com.mybank.service.AccountSummaryService;

@Service
public class AccountSummaryServiceImpl implements AccountSummaryService {

	@Autowired
	TransactionsRepository transactionsRepository;
	@Autowired
	AccountRepository accountRepository;
	//This method use to get the account balance and 
	@Override
	public TansactionsResponse getTansactions(String accountNumber) throws DataNotFoundException {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		
			if(account!=null) {
			List<Transactions> listTransactions = transactionsRepository.findFirst10ByDestinationAccountNumberOrPrimaryAccountNumberOrderByIdDesc(accountNumber,accountNumber);
			TansactionsResponse tansactionsResponse = new TansactionsResponse();
			tansactionsResponse.setAccountNumber(account.getAccountNumber());
			tansactionsResponse.setAvailableBalance(account.getAccountBalance());
			tansactionsResponse.setTransectionList(listTransactions);
			return tansactionsResponse;
			}else {
			throw new DataNotFoundException("Account number "+accountNumber+" is not found");
			}
	}
	
	
	@Override
	public AccountSummaryResponse getAccountSummary(String  accountNumber) throws DataNotFoundException, BeneficiaryException {
	
		// Fetching the Account details for the given account number.
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if(account!=null) {
			AccountSummaryResponse response = new AccountSummaryResponse();
			response.setAccountHolderName(account.getAccountHolderName());
			response.setAccountNumber(account.getAccountNumber());
			response.setAccountType(account.getAccountType());
			response.setAvailableBalance(account.getAccountBalance());
			response.setCreationDate(account.getAccountCreationDate());
			return response;
			
			}else {
				throw new DataNotFoundException("Account number does not exists *****!!");
			}		
	}


}

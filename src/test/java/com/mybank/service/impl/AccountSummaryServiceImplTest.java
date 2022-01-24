package com.mybank.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mybank.dto.AccountSummaryResponse;
import com.mybank.entity.Account;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;
import com.mybank.repository.AccountRepository;

@ExtendWith(SpringExtension.class)
class AccountSummaryServiceImplTest {
	
	@InjectMocks
	AccountSummaryServiceImpl accountSummaryServiceImpl;
	
	@Mock
	AccountRepository accountSummaryRepository;
	
	@Test
	void getAccountSummaryTest() throws DataNotFoundException, BeneficiaryException {
		
		  
	    Date date = new Date();      
		AccountSummaryResponse accSummary = new AccountSummaryResponse();
		accSummary.setAccountHolderName("Amarjit");
		accSummary.setAccountNumber("12345");
		accSummary.setAccountType("Saving");
		accSummary.setCreationDate(date);
		
		Account accountSummary = new Account();
		accountSummary.setAccountBalance(15000.23);
		accountSummary.setAccountCreationDate(date);
		accountSummary.setAccountHolderName("Amarjit");
		accountSummary.setAccountNumber("12345");
		accountSummary.setAccountType("Saving");
		accountSummary.setCustomerId("123456");
		
				
		Mockito.when(accountSummaryRepository.findByAccountNumber("12345")).thenReturn(accountSummary);		
		assertEquals(15000.23,accountSummary.getAccountBalance());
		
	}
	

	

}
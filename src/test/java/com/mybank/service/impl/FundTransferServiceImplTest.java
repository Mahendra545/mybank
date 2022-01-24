package com.mybank.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mybank.dto.TransferRequest;
import com.mybank.entity.Account;
import com.mybank.entity.Beneficiary;
import com.mybank.entity.Transactions;
import com.mybank.exception.MyBankException;
import com.mybank.exception.ObjNotFoundException;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.BeneficiaryRepository;
import com.mybank.repository.TransactionsRepository;




@ExtendWith(SpringExtension.class)
class FundTransferServiceImplTest {

	@InjectMocks
	FundTransferServiceImpl fundTransferServiceImpl;
	
	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionsRepository transactionRepository;

	@Mock
	BeneficiaryRepository beneficiaryRepository;
	
	@Test
	void testMoneyTransferNoBeneificiary() {
		TransferRequest transferRequest = new TransferRequest();
		Account accountDetails = new Account();
		accountDetails.setAccountNumber("1234567890");
		accountDetails.setAccountBalance(5000);
		accountDetails.setAccountHolderName("Test");
		accountDetails.setAccountType("Savings");
		accountDetails.setCustomerId("11421");
		
		Beneficiary beneficiaryAccountDetails = new Beneficiary();
		beneficiaryAccountDetails.setAccountNumber("9876543210");
		beneficiaryAccountDetails.setBankName("MyBank");
		
		transferRequest.setAccountNumber("1234567890");
		transferRequest.setAmount(100);
		transferRequest.setBeneficiaryAccountNumber("9876543210");
		transferRequest.setComments("fee");
		
		Transactions accountTansaction = new Transactions();
		accountTansaction.setAmount(transferRequest.getAmount());
		accountTansaction.setPrimaryAccountNumber(transferRequest.getAccountNumber());
		accountTansaction.setDestinationAccountNumber(transferRequest.getBeneficiaryAccountNumber());
		accountTansaction.setTransactionType("Debit");
		
		Mockito.when(accountRepository.findByAccountNumber(transferRequest.getAccountNumber())).thenReturn(accountDetails);
	
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountNumber(transferRequest.getBeneficiaryAccountNumber())).thenReturn(beneficiaryAccountDetails);
		
		Mockito.when(transactionRepository.save(accountTansaction)).thenReturn(accountTansaction);
		
		//String response = fundTransferServiceImpl.moneyTransfer(transferRequest);
		

		com.mybank.exception.ObjNotFoundException thrown = Assertions.assertThrows(ObjNotFoundException.class, () -> {
               fundTransferServiceImpl.moneyTransfer(transferRequest);
		
		});
	    

		Assertions.assertEquals("Beneficiary Account is not added to your Account",thrown.getMessage());
	
		//assertEquals("Money Transferred Successfully", response.toString());
	}

	@Test
	void testMoneyTransferSuccess() {
		TransferRequest transferRequest = new TransferRequest();
		Account accountDetails = new Account();
		accountDetails.setAccountNumber("1234567890");
		accountDetails.setAccountBalance(5000);
		accountDetails.setAccountHolderName("Test");
		accountDetails.setAccountType("Savings");
		accountDetails.setCustomerId("11421");
		
		Beneficiary beneficiaryAccountDetails = new Beneficiary();
		beneficiaryAccountDetails.setAccountNumber("9876543210");
		beneficiaryAccountDetails.setBankName("MyBank");
		beneficiaryAccountDetails.setAccountNumber("1234567890");
		
		transferRequest.setAccountNumber("1234567890");
		transferRequest.setAmount(100);
		transferRequest.setBeneficiaryAccountNumber("9876543210");
		transferRequest.setComments("fee");
		
		Transactions accountTansaction = new Transactions();
		accountTansaction.setAmount(transferRequest.getAmount());
		accountTansaction.setPrimaryAccountNumber(transferRequest.getAccountNumber());
		accountTansaction.setDestinationAccountNumber(transferRequest.getBeneficiaryAccountNumber());
		accountTansaction.setTransactionType("Debit");
		
		Mockito.when(accountRepository.findByAccountNumber(transferRequest.getAccountNumber())).thenReturn(accountDetails);
	
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountNumber(transferRequest.getBeneficiaryAccountNumber())).thenReturn(beneficiaryAccountDetails);
				
               String response = fundTransferServiceImpl.moneyTransfer(transferRequest);
		
		
		assertEquals("Money Transferred Successfully", response.toString());
	}

	@Test
	void testMoneyTransferFailure() {
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setAccountNumber("1234567890");
		transferRequest.setAmount(100);
		transferRequest.setBeneficiaryAccountNumber("76176712911");
		transferRequest.setComments("fee");
		
		Account accountDetails = new Account();
		accountDetails.setAccountNumber("1234567890");
		accountDetails.setAccountBalance(5000);
		accountDetails.setAccountHolderName("Test");
		accountDetails.setAccountType("Savings");
		accountDetails.setCustomerId("11421");
		
		Mockito.when(accountRepository.findByAccountNumber(transferRequest.getAccountNumber())).thenReturn(accountDetails);
					
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountNumber(transferRequest.getBeneficiaryAccountNumber())).thenReturn(null);

		com.mybank.exception.ObjNotFoundException thrown = Assertions.assertThrows(ObjNotFoundException.class, () -> {
               fundTransferServiceImpl.moneyTransfer(transferRequest);
		
		});
	    

		Assertions.assertEquals("Beneficiary Account is not added to your Account",thrown.getMessage());
	}

	@Test
	void testMoneyTransferFailurePrimaryAccount() {
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setAccountNumber("1234567890");
		transferRequest.setAmount(100);
		transferRequest.setBeneficiaryAccountNumber("76176712911");
		transferRequest.setComments("fee");
		
		Mockito.when(accountRepository.findByAccountNumber(transferRequest.getAccountNumber())).thenReturn(null);
					
		com.mybank.exception.ObjNotFoundException thrown = Assertions.assertThrows(ObjNotFoundException.class, () -> {
               fundTransferServiceImpl.moneyTransfer(transferRequest);
		
		});
	    

		Assertions.assertEquals("primary account does not exist, Please enter valid accountnumber",thrown.getMessage());
	}
	
	@Test
	 void testMoneyTransferFailureInsufficientBalance() {
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setAccountNumber("1234567890");
		transferRequest.setAmount(10000);
		transferRequest.setBeneficiaryAccountNumber("76176712911");
		transferRequest.setComments("fee");
		
		Account accountDetails = new Account();
		accountDetails.setAccountNumber("1234567890");
		accountDetails.setAccountBalance(5000);
		accountDetails.setAccountHolderName("Test");
		accountDetails.setAccountType("Savings");
		accountDetails.setCustomerId("11421");
		
		Mockito.when(accountRepository.findByAccountNumber(transferRequest.getAccountNumber())).thenReturn(accountDetails);
					
        com.mybank.exception.MyBankException thrown = Assertions.assertThrows(MyBankException.class, () -> {
            fundTransferServiceImpl.moneyTransfer(transferRequest);
		
		});
	    

		Assertions.assertEquals("Insufficient Balance",thrown.getMessage());

	}

	@Test
	void testMoneyTransferFailureBwnSameAccs() {
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setAccountNumber("1234567890");
		transferRequest.setAmount(100);
		transferRequest.setBeneficiaryAccountNumber("1234567890");
		transferRequest.setComments("fee");
		
		Account accountDetails = new Account();
		accountDetails.setAccountNumber("1234567890");
		accountDetails.setAccountBalance(5000);
		accountDetails.setAccountHolderName("Test");
		accountDetails.setAccountType("Savings");
		accountDetails.setCustomerId("11421");
		
		Mockito.when(accountRepository.findByAccountNumber(transferRequest.getAccountNumber())).thenReturn(accountDetails);
		
        com.mybank.exception.MyBankException thrown = Assertions.assertThrows(MyBankException.class, () -> {
            fundTransferServiceImpl.moneyTransfer(transferRequest);
		
		});
	    

		Assertions.assertEquals("Source and Destination account shouldn't be same",thrown.getMessage());

	}
	
}
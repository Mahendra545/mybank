package com.mybank.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.dto.TransferRequest;
import com.mybank.entity.Account;
import com.mybank.entity.Beneficiary;
import com.mybank.entity.Transactions;
import com.mybank.exception.InvalidUserCredentials;
import com.mybank.exception.MyBankException;
import com.mybank.exception.ObjNotFoundException;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.BeneficiaryRepository;
import com.mybank.repository.TransactionsRepository;
import com.mybank.service.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {


	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionsRepository transactionRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	private final Logger logger = LogManager.getLogger(FundTransferServiceImpl.class);


	@Override
	@Transactional
	public String moneyTransfer(TransferRequest transactionRequest) {

		String accNumber = transactionRequest.getAccountNumber();
		String beneficiaryAccNumber = transactionRequest.getBeneficiaryAccountNumber();
		double transferAmount = transactionRequest.getAmount();

		Transactions accountTransaction = new Transactions();
		if(!accNumber.equals(beneficiaryAccNumber)) {
			try {
				double accountBalance;
				Account accDetails= accountRepository.findByAccountNumber(accNumber);
				if(accDetails!=null) {
					accountBalance = accDetails.getAccountBalance();
				}else {
					logger.error("account details not found");
					throw new ObjNotFoundException("primary account does not exist, Please enter valid accountnumber");
				}
				if(accountBalance > transferAmount) {
					double updatedBalance = accountBalance -transferAmount;
					accountTransaction.setAmount(transferAmount);
					accountTransaction.setPrimaryAccountNumber(accNumber);
					accountTransaction.setDestinationAccountNumber(beneficiaryAccNumber);
					accountTransaction.setComments(transactionRequest.getComments());
					accountTransaction.setTransactionType("debit");

					Beneficiary beneficiaryAccDetails= beneficiaryRepository.findByBeneficiaryAccountNumber(beneficiaryAccNumber);
					
					if(beneficiaryAccDetails!=null && beneficiaryAccDetails.getAccountNumber().equals(accNumber) ) {
						this.depositMoney(beneficiaryAccNumber, transferAmount);
						this.updateBalance(accDetails, updatedBalance);
						transactionRepository.save(accountTransaction);
						return "Money Transferred Successfully";

				}else {
					throw new ObjNotFoundException("Beneficiary Account is not added to your Account");
				}

			}else {
				logger.error("Insufficient Balance");
				throw new MyBankException("Insufficient Balance");
			}

		}catch (Exception e) {
			logger.error("Transaction got failed with "+e.getMessage());
			throw e;
		}
	}else {
		logger.error("Source and Destination account shouldn't be same");
		throw new MyBankException("Source and Destination account shouldn't be same");
	}

}


public Account updateBalance(Account accDetails,double balance) {
	accDetails.setAccountBalance(balance);
	try {
		return accountRepository.save(accDetails);
	}catch (Exception e) {
		logger.error("Exception occured while persisting into db"+e.getMessage());
		throw e;
	}
}



@Override
public boolean validateAccountDetailsByRequest(String userName, String accountNumber) throws InvalidUserCredentials {
	Account accDetails = accountRepository.findByAccountNumber(accountNumber);
	if(accDetails!=null) {
	return accDetails.getCustomerId().equals(userName);
	}else {
		throw new InvalidUserCredentials("Not authorized to perform operation");
	}
}

private Account depositMoney(String beneficiaryAccNumber, double transferAmount) {

	try {
		Account accDetails= accountRepository.findByAccountNumber(beneficiaryAccNumber);
		if(accDetails!=null) {
			double accountBalance = accDetails.getAccountBalance();
			accDetails.setAccountBalance(accountBalance + transferAmount);
			return accountRepository.save(accDetails);
		}else {
			logger.info("Beneficiary account details are not found in MyBank");
		}
	}catch (Exception e) {
		logger.error("Exception occured while transfering money "+e.getMessage());
		throw e;
	}
	return null;

}

}

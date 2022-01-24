package com.mybank.service;


import com.mybank.dto.TransferRequest;
import com.mybank.exception.DataNotFoundException;

public interface FundTransferService {

	String moneyTransfer(TransferRequest transferRequest);

	boolean validateAccountDetailsByRequest(String authToken, String accountNumber) throws DataNotFoundException;
}

package com.mybank.controller;

import static com.mybank.constants.Constants.TOKEN_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.config.JwtTokenUtil;
import com.mybank.dto.AccountSummaryResponse;
import com.mybank.dto.TansactionsResponse;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;
import com.mybank.service.AccountSummaryService;
import com.mybank.service.FundTransferService;

@RestController
@RequestMapping("/account")
public class AccountSummaryController {

	@Autowired
	AccountSummaryService accountSummaryService;

	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@GetMapping("/transactions/{accountNumber}")
	public ResponseEntity<TansactionsResponse> getTansactions(@PathVariable String accountNumber, @RequestHeader (name="Authorization") String token) throws DataNotFoundException {		
		String userName = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));

		if(fundTransferService.validateAccountDetailsByRequest(userName, accountNumber)) {

			TansactionsResponse response = accountSummaryService.getTansactions(accountNumber);
			if(response!=null) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


	@GetMapping("/summary/{accountNumber}")
	public ResponseEntity<AccountSummaryResponse> getAccountSummary(@PathVariable String accountNumber, @RequestHeader (name="Authorization") String token ) throws DataNotFoundException, BeneficiaryException {

		String userName = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));

		if(fundTransferService.validateAccountDetailsByRequest(userName, accountNumber)) {

			AccountSummaryResponse response = accountSummaryService.getAccountSummary(accountNumber);
			if(response!=null) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}
	
	
	
}


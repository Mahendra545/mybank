package com.mybank.controller;

import static com.mybank.constants.Constants.TOKEN_PREFIX;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.config.JwtTokenUtil;
import com.mybank.dto.TransferRequest;
import com.mybank.exception.DataNotFoundException;
import com.mybank.service.FundTransferService;

@RestController
public class FundTransferController {

	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;


	@PostMapping("/fundTransfer")
	public ResponseEntity<Object> fundTransfer(@RequestBody @Valid TransferRequest transferRequest, @RequestHeader (name="Authorization") String token) throws DataNotFoundException {
		
		String userName = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));

		if(fundTransferService.validateAccountDetailsByRequest(userName, transferRequest.getAccountNumber())) {
			
			String response = fundTransferService.moneyTransfer(transferRequest);
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

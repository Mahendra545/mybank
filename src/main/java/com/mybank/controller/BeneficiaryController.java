package com.mybank.controller;

import static com.mybank.constants.Constants.TOKEN_PREFIX;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.config.JwtTokenUtil;
import com.mybank.dto.BeneficiaryResponse;
import com.mybank.dto.BeneficiarySummaryDto;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;
import com.mybank.service.BeneficiaryService;
import com.mybank.service.FundTransferService;

@RestController
public class BeneficiaryController {

	@Autowired
	BeneficiaryService beneficiaryService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	FundTransferService fundTransferService;

	@GetMapping("/beneficiary/{accountNumber}/{pageNo}/{pageSize}")
	public ResponseEntity<List<BeneficiaryResponse>> getBeneficiary(@PathVariable String accountNumber, @PathVariable int pageNo,@PathVariable int pageSize, @RequestHeader (name="Authorization") String token) throws DataNotFoundException{

		String userName = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));

		if(fundTransferService.validateAccountDetailsByRequest(userName, accountNumber)) {
			List<BeneficiaryResponse> response = beneficiaryService.getBeneficiary(accountNumber,pageNo,pageSize);
			if(response!=null) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping("/beneficiary")
	public ResponseEntity<String> addBenificiary(@Valid @RequestBody BeneficiarySummaryDto beneficiarySummary, @RequestHeader (name="Authorization") String token ) throws DataNotFoundException, BeneficiaryException {

		String userName = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));

		if(fundTransferService.validateAccountDetailsByRequest(userName, beneficiarySummary.getAccountNumber())) {
			String response = beneficiaryService.addBenificiary(beneficiarySummary);
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

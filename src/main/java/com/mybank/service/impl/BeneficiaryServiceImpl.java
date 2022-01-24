package com.mybank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mybank.dto.BeneficiaryResponse;
import com.mybank.dto.BeneficiarySummaryDto;
import com.mybank.entity.Account;
import com.mybank.entity.Beneficiary;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.BeneficiaryRepository;
import com.mybank.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	@Autowired
	AccountRepository accountRepository;
	//This method use to get the all beneficiary based on the customer account number
	@Override
	public List<BeneficiaryResponse> getBeneficiary(String accountNumber,int pageNo, int pageSize) throws DataNotFoundException {
		List<BeneficiaryResponse> beneficiaryResponseList= new ArrayList<>();
		Pageable paging = PageRequest.of(pageNo,pageSize);
		List<Beneficiary> listBeneficiary = beneficiaryRepository.findByAccountNumber(accountNumber,paging);
		if(!listBeneficiary.isEmpty()) {
			listBeneficiary.forEach(beneficiary -> {
			BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse();
			beneficiaryResponse.setBeneficiaryAccountNumber(beneficiary.getBeneficiaryAccountNumber());
			beneficiaryResponse.setBeneficiaryBankName(beneficiary.getBankName());
			beneficiaryResponse.setBeneficiaryName(beneficiary.getShortName());
			beneficiaryResponseList.add(beneficiaryResponse);
		});
		return beneficiaryResponseList;
		}
		else {
			throw new DataNotFoundException("Beneficiary details are not found for the account number :"+accountNumber);
		}
	}
	
	@Override
	public String addBenificiary(BeneficiarySummaryDto beneficiarySummaryDto) throws DataNotFoundException, BeneficiaryException {
	
		
		// Fetching the account summary details for the given account no		
		Account account = accountRepository.findByAccountNumber(beneficiarySummaryDto.getAccountNumber());
		
		if(account!=null) {
			
			// check for beneficiary account no 
			// checking same beneficiary not allowed to add twice
			// Beneficiry account limit count not more than 10
			List<Beneficiary> list = beneficiaryRepository.findByAccountNumber(beneficiarySummaryDto.getAccountNumber());
			Optional<Beneficiary> beneficiary = beneficiaryRepository.findByAccountNumberAndBeneficiaryAccountNumber(beneficiarySummaryDto.getAccountNumber(), beneficiarySummaryDto.getBeneficiaryAaccountNnumber());
			if(list.size()<11) {
				
				if(!beneficiary.isPresent()) {
					Beneficiary beneficiarySummary = new Beneficiary();
					beneficiarySummary.setAccountNumber(beneficiarySummaryDto.getAccountNumber());
					beneficiarySummary.setBankName(beneficiarySummaryDto.getBankName());
					beneficiarySummary.setBeneficiaryAccountNumber(beneficiarySummaryDto.getBeneficiaryAaccountNnumber());
					beneficiarySummary.setIfsc(beneficiarySummaryDto.getIfsc());
					beneficiarySummary.setShortName(beneficiarySummaryDto.getShortName());
					
					
					beneficiaryRepository.save(beneficiarySummary);
					return "Successfully Beneficiary added !!";
				}else {
					
					throw new BeneficiaryException("User is not allowed to add same beneficiary accountNumber more than once !!");
				}
								
			
			}else {
				throw new BeneficiaryException("User added maximum beneficiary!!");
			}
		}else {
			throw new DataNotFoundException("Account number "+beneficiarySummaryDto.getAccountNumber()+" is not found");
		}
		
	}
	


}

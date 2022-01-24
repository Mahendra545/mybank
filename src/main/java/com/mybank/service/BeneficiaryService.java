package com.mybank.service;

import java.util.List;

import com.mybank.dto.BeneficiaryResponse;
import com.mybank.dto.BeneficiarySummaryDto;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;

public interface BeneficiaryService {

	List<BeneficiaryResponse> getBeneficiary(String accountNumber, int pageNo,int pageSize) throws DataNotFoundException;

	 String addBenificiary(BeneficiarySummaryDto beneficiarySummary)
			throws DataNotFoundException, BeneficiaryException;

	

}

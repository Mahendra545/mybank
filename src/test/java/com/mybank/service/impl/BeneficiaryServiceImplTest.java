package com.mybank.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mybank.dto.BeneficiaryResponse;
import com.mybank.dto.BeneficiarySummaryDto;
import com.mybank.entity.Beneficiary;
import com.mybank.exception.BeneficiaryException;
import com.mybank.exception.DataNotFoundException;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.BeneficiaryRepository;

@ExtendWith(SpringExtension.class)
class BeneficiaryServiceImplTest {

	@InjectMocks
	private BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Mock
	private BeneficiaryRepository beneficiaryRepository;
	@Mock
	private AccountRepository accountRepository;

	@Test
	void getBeneficiary() throws DataNotFoundException {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAccountNumber("123456789");
		beneficiary.setBankName("testbank");
		beneficiary.setBeneficiaryAccountNumber("542631");
		beneficiary.setId(5);
		beneficiary.setIfsc("test");
		beneficiary.setShortName("testname");
		List<Beneficiary> listBeneficiary = new ArrayList<>();
		listBeneficiary.add(beneficiary);
		Pageable paging = PageRequest.of(0, 2);
		when(beneficiaryRepository.findByAccountNumber("123456789", paging)).thenReturn(listBeneficiary);
		List<BeneficiaryResponse> dbBeneficiary = beneficiaryServiceImpl.getBeneficiary("123456789", 0, 2);
		assertEquals("testname", dbBeneficiary.get(0).getBeneficiaryName());
	}

	@Test
	void getBeneficiaryException() throws DataNotFoundException {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAccountNumber("123456789");
		beneficiary.setBankName("testbank");
		beneficiary.setBeneficiaryAccountNumber("542631");
		beneficiary.setId(5);
		beneficiary.setIfsc("test");
		beneficiary.setShortName("testname");
		List<Beneficiary> listBeneficiary = new ArrayList<>();
		listBeneficiary.add(beneficiary);
		Pageable paging = PageRequest.of(0, 2);
		when(beneficiaryRepository.findByAccountNumber("123456789", paging)).thenReturn(listBeneficiary);

		assertThrows(DataNotFoundException.class, () -> {

			beneficiaryServiceImpl.getBeneficiary("123456780", 0, 2);
		});
	}

	@Test
	void getBeneficiaryFail() throws DataNotFoundException {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAccountNumber("123456789");
		beneficiary.setBankName("testbank");
		beneficiary.setBeneficiaryAccountNumber("542631");
		beneficiary.setId(5);
		beneficiary.setIfsc("test");
		beneficiary.setShortName("testname");
		List<Beneficiary> listBeneficiary = new ArrayList<>();
		listBeneficiary.add(beneficiary);
		Pageable paging = PageRequest.of(0, 2);
		when(beneficiaryRepository.findByAccountNumber("123456789", paging)).thenReturn(listBeneficiary);
		List<BeneficiaryResponse> dbBeneficiary = beneficiaryServiceImpl.getBeneficiary("123456789", 0, 2);
		assertNotEquals("test", dbBeneficiary.get(0).getBeneficiaryName());
	}


	  @Test
	  void addBeneficiary() throws DataNotFoundException, BeneficiaryException{ 
	  Beneficiary beneficiary = new Beneficiary();
	  beneficiary.setAccountNumber("123456789");
	  beneficiary.setBankName("testbank");
	  beneficiary.setBeneficiaryAccountNumber("542631"); 
	  beneficiary.setId(5);
	  beneficiary.setIfsc("test"); 
	  beneficiary.setShortName("testname");
	  BeneficiarySummaryDto beneficiarySummaryDto = new BeneficiarySummaryDto();
	  beneficiarySummaryDto.setAccountNumber("123456789");
	  beneficiarySummaryDto.setBankName("testbank");
	  beneficiarySummaryDto.setBeneficiaryAaccountNnumber("542631");
	  beneficiarySummaryDto.setIfsc("test");
	  beneficiarySummaryDto.setShortName("testname");
	  when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
	  beneficiaryServiceImpl.addBenificiary(beneficiarySummaryDto);
	  assertEquals(beneficiarySummaryDto.getAccountNumber(), beneficiary.getAccountNumber()); 
	  }
	  
	  @Test
	  void addBeneficiaryFail() throws DataNotFoundException, BeneficiaryException{ 
	  Beneficiary beneficiary = new Beneficiary();
	  beneficiary.setAccountNumber("123456789");
	  beneficiary.setBankName("testbank");
	  beneficiary.setBeneficiaryAccountNumber("542631"); 
	  beneficiary.setId(5);
	  beneficiary.setIfsc("test");
	  beneficiary.setShortName("testname");
	  when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
	  assertNotEquals("123456", beneficiary.getAccountNumber()); 
	  }
	  
	  
	 
}

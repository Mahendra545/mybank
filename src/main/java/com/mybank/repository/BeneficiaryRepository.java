package com.mybank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends CrudRepository<Beneficiary, Integer> {

	List<Beneficiary> findByAccountNumber(String accountNumber,Pageable paging);

	Beneficiary findByBeneficiaryAccountNumber(String beneficiaryAccNumber);

	List<Beneficiary> findByAccountNumber(String accountnumber);

	Optional<Beneficiary> findByAccountNumberAndBeneficiaryAccountNumber(String accountNumber,
			String beneficiaryAaccountNnumber);

}

package com.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entity.Transactions;
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{

	List<Transactions> findFirst10ByDestinationAccountNumberOrPrimaryAccountNumberOrderByIdDesc(String accountNumber,
			String primaryAccountNumber);

}

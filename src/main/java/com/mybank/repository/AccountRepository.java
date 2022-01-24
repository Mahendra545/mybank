package com.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByAccountNumber(String accountNumber);

	

}

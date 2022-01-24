package com.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entity.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

}

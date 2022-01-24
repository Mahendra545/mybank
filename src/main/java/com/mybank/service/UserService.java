package com.mybank.service;

import java.util.List;

import com.mybank.entity.Users;

public interface UserService {

	List<Users> findAll();

	Users findOne(String username);
}
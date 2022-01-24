package com.mybank.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.mybank.entity.Users;
import com.mybank.exception.InvalidUserCredentials;
import com.mybank.repository.UsersRepository;
import com.mybank.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	UsersRepository usersRepository;
	
	private final Logger logger = LogManager.getLogger(UserServiceImpl.class);


	public UserDetails loadUserByUsername(String username){
		Optional<Users> user = usersRepository.findById(username);
		if(user.isPresent()){
			Users userInfo = user.get();
			return new org.springframework.security.core.userdetails.User(userInfo.getCustomerId(), userInfo.getPassword(), getAuthority());
		}else {
			logger.error("Invalid username or password");
			throw new InvalidUserCredentials("Invalid username or password.");
		}
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<Users> findAll() {
		List<Users> list = new ArrayList<>();
		usersRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}


	@Override
	public Users findOne(String username) {
		Optional<Users> user = usersRepository.findById(username);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
	}


}
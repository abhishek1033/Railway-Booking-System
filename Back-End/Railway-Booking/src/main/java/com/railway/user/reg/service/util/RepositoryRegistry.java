package com.railway.user.reg.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.railway.user.reg.service.repository.UserDetailsRepository;

@Component
public class RepositoryRegistry {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	public UserDetailsRepository getUserDetailsRepository() {
		return userDetailsRepository;
	}

	public void setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
		this.userDetailsRepository = userDetailsRepository;
	}
	
	
}

package com.railway.user.reg.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.railway.user.reg.service.service.UserDetailsService;

@Component
public class ServiceRegistry 
{
	@Autowired
	private UserDetailsService userDetailsService;

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
	
	
}

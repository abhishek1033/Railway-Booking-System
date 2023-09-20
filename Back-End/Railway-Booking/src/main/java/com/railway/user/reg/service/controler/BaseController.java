package com.railway.user.reg.service.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.railway.user.reg.service.util.ApplicationUtils;
import com.railway.user.reg.service.util.RepositoryRegistry;
import com.railway.user.reg.service.util.ServiceRegistry;


public class BaseController {
	
	@Autowired
	ServiceRegistry serviceRegistry;
	
	@Autowired
	RepositoryRegistry repositoryRegistry;
	
	@Autowired
	ApplicationUtils applicationUtils;
	
	@Autowired
	MessageSource messageSource;
	
}

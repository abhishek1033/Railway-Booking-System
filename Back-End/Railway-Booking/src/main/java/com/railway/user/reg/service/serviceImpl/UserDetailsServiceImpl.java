package com.railway.user.reg.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.railway.user.reg.service.entity.UserDetails;
import com.railway.user.reg.service.service.UserDetailsService;
import com.railway.user.reg.service.util.RepositoryRegistry;

@Service
public class UserDetailsServiceImpl extends RepositoryRegistry implements UserDetailsService{

	@Override
	public UserDetails addNewUser(UserDetails user) 
	{
		user.setUserId(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
		user.setCreatedAt(new Date());
		user.setCreatedBy("admin");
		user.setStatus("pending");
		getUserDetailsRepository().save(user);
		return user;
	}

	@Override
	public UserDetails getUserDetailsByUserId(String userId) 
	{
		UserDetails user = getUserDetailsRepository().findByUserId(userId);
		return user;
	}

	@Override
	public List<UserDetails> getAllUserDetails() {
		
		List<UserDetails> list = getUserDetailsRepository().findAll();
		return list;
	}

	@Override
	public UserDetails updateUser(UserDetails oldDetails, UserDetails newDetails) {
		
		oldDetails.setUserName(newDetails.getUserName());
		oldDetails.setFirstName(newDetails.getFirstName());
		oldDetails.setMiddleName(newDetails.getMiddleName());
		oldDetails.setLastName(newDetails.getLastName());
		oldDetails.setGender(newDetails.getGender());
		oldDetails.setEmailId(newDetails.getEmailId());
		oldDetails.setMobileNo(newDetails.getMobileNo());
		oldDetails.setPassword(newDetails.getPassword());
		oldDetails.setSecurityQuestion(newDetails.getSecurityQuestion());
		oldDetails.setSecurityAnswer(newDetails.getSecurityAnswer());
		oldDetails.setPreferredLanguage(newDetails.getPreferredLanguage());
		oldDetails.setDob(newDetails.getDob());
		oldDetails.setMarritalStatus(newDetails.getMarritalStatus());
		oldDetails.setNationality(newDetails.getNationality());
		oldDetails.setCountry(newDetails.getCountry());
		oldDetails.setState(newDetails.getState());
		oldDetails.setCity(newDetails.getCity());
		oldDetails.setAddressNo(newDetails.getAddressNo());
		oldDetails.setStreet(newDetails.getStreet());
		oldDetails.setArea(newDetails.getArea());
		oldDetails.setPincode(newDetails.getPincode());
		oldDetails.setUpdatedAt(new Date());
		oldDetails.setUpdatedBy("USER");
		getUserDetailsRepository().save(oldDetails);
		
		return oldDetails;
	}

	

	

}

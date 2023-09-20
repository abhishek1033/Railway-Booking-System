package com.railway.user.reg.service.service;

import java.util.List;

import com.railway.user.reg.service.entity.UserDetails;

public interface UserDetailsService {

	UserDetails addNewUser(UserDetails userDto);

	UserDetails getUserDetailsByUserId(String userId);

	List<UserDetails> getAllUserDetails();

	UserDetails updateUser(UserDetails oldDetails, UserDetails newDetails);

}

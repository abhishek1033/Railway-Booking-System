package com.railway.user.reg.service.repository;

import org.springframework.stereotype.Repository;

import com.railway.user.reg.service.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends BaseRepository<UserDetails, String>{

	UserDetails findByUserId(String userId);

}

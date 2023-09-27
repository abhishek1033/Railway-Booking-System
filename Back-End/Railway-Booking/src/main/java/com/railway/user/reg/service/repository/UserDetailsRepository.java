package com.railway.user.reg.service.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.railway.user.reg.service.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends BaseRepository<UserDetails, String>{

	UserDetails findByUserId(String userId);

	@Modifying
    @Transactional
	@Query(value = "delete FROM UserDetails where userId=:userId")
	void deleteUserByUserId(@Param("userId") String userId);

}

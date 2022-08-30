package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.UserInfo;

public interface UserInfoRepository extends JpaSpecificationExecutor<UserInfo>, PagingAndSortingRepository<UserInfo, Long> {

	UserInfo findFirstByRefNoAndStatusNull(String refNo);
	
	List<UserInfo> findByUserTokenAndStatus(String userToken, String status);
	
	UserInfo findFirstByUserTokenAndStatus(String userToken, String status);
	
	UserInfo findFirstByUserTokenAndRoleAndStatus(String userToken, String role, String status);
	
	UserInfo findFirstByOwnerId(String ownerId);
	
	@Transactional
	@Modifying
	@Query("delete UserInfo x where x.ownerId = ?1 ")
	int deleteByOwnerId(String ownerId);
}


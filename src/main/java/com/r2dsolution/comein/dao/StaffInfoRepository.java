package com.r2dsolution.comein.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.StaffInfo;

public interface StaffInfoRepository extends JpaSpecificationExecutor<StaffInfo>, PagingAndSortingRepository<StaffInfo, Long> {

	StaffInfo findByRefNo(String refNo);
	
	StaffInfo findFirstByOwnerId(String ownerId);
	
	@Transactional
	@Modifying
	@Query("update StaffInfo x set x.ownerId = ?1 where x.ownerId = ?2 ")
	int updateOwnerIdByOwnerId(String ownerId, String tmpOwnerId);
	
	@Transactional
	@Modifying
	@Query("delete StaffInfo x where x.ownerId = ?1 ")
	int deleteByOwnerId(String ownerId);
}


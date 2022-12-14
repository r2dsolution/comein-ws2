package com.r2dsolution.comein.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourCompany;

public interface TourCompanyRepository extends JpaSpecificationExecutor<TourCompany>, PagingAndSortingRepository<TourCompany, Long> {

	TourCompany findByRefNo(String refNo);
	
	TourCompany findFirstByOwnerId(String ownerId);
	
	@Transactional
	@Modifying
	@Query("update TourCompany x set x.ownerId = ?1 where x.ownerId = ?2 ")
	int updateOwnerIdByOwnerId(String ownerId, String tmpOwnerId);
	
	@Transactional
	@Modifying
	@Query("delete TourCompany x where x.ownerId = ?1 ")
	int deleteByOwnerId(String ownerId);
	
	int countByCompanyNameContainingIgnoreCase(String companyName);
}


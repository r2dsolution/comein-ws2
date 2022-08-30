package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.HotelInfo;

//@RepositoryRestResource(collectionResourceRel = "hotels", path = "hotels")
public interface HotelInfoRepository extends JpaSpecificationExecutor<HotelInfo>, PagingAndSortingRepository<HotelInfo, Long> {

	HotelInfo findByRefNo(String refNo);
	
	HotelInfo findFirstByOwnerId(String ownerId);
	
	List<HotelInfo> findByStatus(String status);
	
	@Transactional
	@Modifying
	@Query("update HotelInfo x set x.ownerId = ?1 where x.ownerId = ?2 ")
	int updateOwnerIdByOwnerId(String ownerId, String tmpOwnerId);
	
	@Transactional
	@Modifying
	@Query("delete HotelInfo x where x.ownerId = ?1 ")
	int deleteByOwnerId(String ownerId);
}


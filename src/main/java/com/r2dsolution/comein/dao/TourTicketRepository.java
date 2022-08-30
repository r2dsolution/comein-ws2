package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourTicket;

public interface TourTicketRepository extends JpaSpecificationExecutor<TourTicket>, PagingAndSortingRepository<TourTicket, Long> {

	@Transactional
	@Modifying
	@Query("update TourTicket x set x.status = ?1 where x.inventoryId = ?2 ")
	int disableTicketByInventory(String status, long inventoryId);
	
	@Transactional
	@Modifying
	@Query("update TourTicket x set x.status = ?1 where x.id in ?2 ")
	int updateByTicketIdIn(String status, List<Long> ticketIds);
}


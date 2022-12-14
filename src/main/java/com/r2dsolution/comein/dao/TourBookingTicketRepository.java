package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourBookingTicket;

public interface TourBookingTicketRepository extends JpaSpecificationExecutor<TourBookingTicket>, PagingAndSortingRepository<TourBookingTicket, Long> {
	
	List<Long> findTicketIdByBookingId(long bookingId);
	
}


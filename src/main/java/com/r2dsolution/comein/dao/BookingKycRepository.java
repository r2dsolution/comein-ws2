package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.BookingKyc;

public interface BookingKycRepository extends JpaSpecificationExecutor<BookingKyc>, PagingAndSortingRepository<BookingKyc, Long> {

	List<BookingKyc> findByBookingIdOrderById(long bookingId);
	
	BookingKyc findFirstByRefId(String refId);
}


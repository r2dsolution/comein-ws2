package com.r2dsolution.comein.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourBookingView;

public interface TourBookingViewRepository extends JpaSpecificationExecutor<TourBookingView>, PagingAndSortingRepository<TourBookingView, Long> {

	TourBookingView findFirstByBookingCodeAndStatus(String bookingCode, String status);
	
	List<TourBookingView> findByTourIdAndTourDateAndStatus(Long tourId, LocalDate tourDate, String status);
	
}


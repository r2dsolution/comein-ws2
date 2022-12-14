package com.r2dsolution.comein.dao;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourBooking;

public interface TourBookingRepository extends JpaSpecificationExecutor<TourBooking>, PagingAndSortingRepository<TourBooking, Long> {
	
	int countByBookingCodeAndStatus(String bookingCode, String status);

	TourBooking findFirstByBookingCodeAndStatus(String bookingCode, String status);

	@Transactional
	@Modifying
	@Query("update TourBooking x set x.status = ?1, x.updatedBy = ?2, x.updatedDate = CURRENT_TIMESTAMP where x.bookingCode = ?3 and x.status = ?4 ")
	int cancelTourBooking(String statusCancel, String userToken, String bookingCode, String statusVerify);

	@Transactional
	@Modifying
	@Query("update TourBooking x set x.tourDate = ?1, x.updatedBy = ?2, x.updatedDate = CURRENT_TIMESTAMP where x.bookingCode = ?3 and x.status = ?4 ")
	int updateTourBooking(LocalDate tourDate, String userToken, String bookingCode, String statusVerify);
}


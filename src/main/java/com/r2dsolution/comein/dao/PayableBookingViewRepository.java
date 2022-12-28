package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PayableBookingView;

public interface PayableBookingViewRepository extends JpaSpecificationExecutor<PayableBookingView>, PagingAndSortingRepository<PayableBookingView, Long> {

	List<PayableBookingView> findAll();

	List<PayableBookingView> findByCompanyId(Long companyId);

	PayableBookingView findFirstByBookingCode(String bookingCode);
}


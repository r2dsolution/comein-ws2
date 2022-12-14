package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.BookingInfo;

public interface BookingRepository extends JpaSpecificationExecutor<BookingInfo>, PagingAndSortingRepository<BookingInfo, Long> {

	int countByOwnerId(String ownerId);
}


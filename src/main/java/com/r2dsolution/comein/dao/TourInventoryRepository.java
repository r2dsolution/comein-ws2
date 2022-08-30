package com.r2dsolution.comein.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourInventory;

public interface TourInventoryRepository extends JpaSpecificationExecutor<TourInventory>, PagingAndSortingRepository<TourInventory, Long> {

	int countByTourIdAndTourDate(long tourId, LocalDate tourDate);
	
	List<TourInventory> findByTourIdAndTourDateGreaterThanEqualAndTourDateLessThanEqualOrderByTourDateAsc(long tourId, LocalDate startDate, LocalDate endDate);
}


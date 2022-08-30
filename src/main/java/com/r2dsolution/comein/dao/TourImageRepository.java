package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourImage;

public interface TourImageRepository extends JpaSpecificationExecutor<TourImage>, PagingAndSortingRepository<TourImage, Long> {

	List<TourImage> findByTourIdOrderBySeq(Long tourId);
	
	@Transactional
	void deleteByTourId(Long tourId);
}


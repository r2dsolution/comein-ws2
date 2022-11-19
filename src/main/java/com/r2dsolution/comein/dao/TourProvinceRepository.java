package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourProvince;

public interface TourProvinceRepository extends JpaSpecificationExecutor<TourProvince>, PagingAndSortingRepository<TourProvince, Long> {

	List<TourProvince> findByTourIdOrderByProvince(Long tourId);
	
	@Transactional
	void deleteByTourId(Long tourId);
}


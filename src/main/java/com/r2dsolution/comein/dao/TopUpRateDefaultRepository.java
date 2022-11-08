package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TopupRateDefault;

public interface TopUpRateDefaultRepository extends JpaSpecificationExecutor<TopupRateDefault>, PagingAndSortingRepository<TopupRateDefault, Long> {

	List<TopupRateDefault> findAll();
}


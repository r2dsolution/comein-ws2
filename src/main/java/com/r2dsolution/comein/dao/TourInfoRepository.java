package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TourInfo;

public interface TourInfoRepository extends JpaSpecificationExecutor<TourInfo>, PagingAndSortingRepository<TourInfo, Long> {

}


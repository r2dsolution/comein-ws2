package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PayableTourView;

public interface PayableTourViewRepository extends JpaSpecificationExecutor<PayableTourView>, PagingAndSortingRepository<PayableTourView, Long> {

	List<PayableTourView> findByCompanyId(Long companyId);
}


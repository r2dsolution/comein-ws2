package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PayablePeriod;

public interface PayablePeriodRepository extends JpaSpecificationExecutor<PayablePeriod>, PagingAndSortingRepository<PayablePeriod, Long> {

	List<PayablePeriod> findByCompanyIdAndPeriodTypeAndStatus(Long companyId, String periodType, String status);
}


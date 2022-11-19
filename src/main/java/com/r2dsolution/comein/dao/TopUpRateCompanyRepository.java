package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.TopupRateCompany;

public interface TopUpRateCompanyRepository extends JpaSpecificationExecutor<TopupRateCompany>, PagingAndSortingRepository<TopupRateCompany, Long> {

	List<TopupRateCompany> findByCompanyId(Long companyId);
	
	@Transactional
	void deleteByCompanyId(Long companyId);
}


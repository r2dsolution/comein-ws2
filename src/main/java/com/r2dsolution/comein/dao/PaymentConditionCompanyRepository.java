package com.r2dsolution.comein.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PaymentConditionCompany;

public interface PaymentConditionCompanyRepository extends JpaSpecificationExecutor<PaymentConditionCompany>, PagingAndSortingRepository<PaymentConditionCompany, Long> {

	List<PaymentConditionCompany> findByCompanyId(Long companyId);
	
	@Transactional
	void deleteByCompanyId(Long companyId);
}


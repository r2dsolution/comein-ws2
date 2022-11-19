package com.r2dsolution.comein.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.CompanyBookingSummary;

public interface CompanyBookingSummaryRepository extends JpaSpecificationExecutor<CompanyBookingSummary>, PagingAndSortingRepository<CompanyBookingSummary, Long> {

	List<CompanyBookingSummary> findByCompanyIdAndTourDateGreaterThanEqualAndTourDateLessThanEqualAndStatus(Long companyId, LocalDate dateFrom, LocalDate dateTo, String status);

}


package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PayableTourView;

public interface PayableTourViewRepository extends JpaSpecificationExecutor<PayableTourView>, PagingAndSortingRepository<PayableTourView, Long> {

	List<PayableTourView> findByCompanyIdAndStatus(Long companyId, String status);
	
	@Query("select c.periodId as period_id, c.tourId as tour_id, c.tourName as tour_name, c.note, sum(c.netValue) as total_net_value from PayableTourView c "
			+ "where c.periodId = ?1 and c.status = ?2 group by c.periodId, c.tourId, c.tourName, c.note")
	List<Object[]> sumPayableTourByPeriod(Long periodId, String status);
	
	List<PayableTourView> findByPeriodIdAndTourIdAndStatus(Long periodId, Long tourId, String status);
}


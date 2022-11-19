package com.r2dsolution.comein.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.DashboardTourBooking;
import com.r2dsolution.comein.entity.DashboardTourBookingPK;

public interface DashboardTourBookingRepository extends JpaSpecificationExecutor<DashboardTourBooking>, PagingAndSortingRepository<DashboardTourBooking, DashboardTourBookingPK> {

//	@Query(value = "select c from DashboardTourBooking c where c.id.tourCompanyId = ?1 and c.id.tourDate >= ?2 and c.id.tourDate <= ?3 order by c.id.tourName, c.id.tourDate ")
//	List<DashboardTourBooking> findByCompanyAndTourDate(Long companyId, LocalDate dateFrom, LocalDate dateTo);
	
	List<DashboardTourBooking> findByIdTourCompanyIdAndIdTourDateGreaterThanEqualAndIdTourDateLessThanEqualOrderByIdTourNameAscIdTourDateAsc(Long tourCompanyId, LocalDate dateFrom, LocalDate dateTo);
}


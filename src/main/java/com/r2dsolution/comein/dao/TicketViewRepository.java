package com.r2dsolution.comein.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.VTicket;
import com.r2dsolution.comein.entity.VTicketPK;

public interface TicketViewRepository extends JpaSpecificationExecutor<VTicket>, PagingAndSortingRepository<VTicket, VTicketPK> {

	int countByIdTourIdAndIdTourDateAndTicketStatus(long tourId, LocalDate tourDate, String status);
	
	List<VTicket> findByIdTourIdAndIdTourDateGreaterThanEqualAndIdTourDateLessThanEqualAndTicketStatusOrderByIdTourDateAsc(long tourId, LocalDate startDate, LocalDate endDate, String status);
}


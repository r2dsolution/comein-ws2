package com.r2dsolution.comein.spec;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2dsolution.comein.entity.OtaBooking;

public class OtaBookingSpecification implements Specification<OtaBooking>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OtaBooking filter;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public OtaBookingSpecification(OtaBooking filter, LocalDate startDate, LocalDate endDate) {
		super();
		this.filter = filter;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public Predicate toPredicate(Root<OtaBooking> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(startDate != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("feedDate"), startDate));
		}
		if(endDate != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("feedDate"), endDate));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
	private static String genContainLikePattern(String data) {
		if(data == null || data.isEmpty()) {
			return "%";
		} else {
			return "%" + data + "%";
		}
	}

}

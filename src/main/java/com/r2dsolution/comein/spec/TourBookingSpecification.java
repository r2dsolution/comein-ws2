package com.r2dsolution.comein.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2dsolution.comein.entity.TourBooking;
import com.r2dsolution.comein.util.StringUtils;

public class TourBookingSpecification implements Specification<TourBooking>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TourBooking filter;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public TourBookingSpecification(TourBooking filter, LocalDate startDate, LocalDate endDate) {
		super();
		this.filter = filter;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public Predicate toPredicate(Root<TourBooking> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(this.filter != null) {
			String bookingCode = this.filter.getBookingCode();
			if(!StringUtils.isEmpty(bookingCode)) {
				predicates.add(cb.equal(cb.upper(root.get("bookingCode")), bookingCode.toUpperCase()));
			}
			String refName = this.filter.getReferenceName();
			if(!StringUtils.isEmpty(refName)) {
				refName = genContainLikePattern(refName.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("referenceName")), refName));
			}
		}
		if(startDate != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("bookingDate"), startDate));
		}
		if(endDate != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("bookingDate"), endDate));
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

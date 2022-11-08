package com.r2dsolution.comein.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import com.r2dsolution.comein.util.ObjectUtils;

import com.r2dsolution.comein.entity.TourBookingView;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.StringUtils;

public class TourBookingViewSpecification implements Specification<TourBookingView>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DATE_TYPE_TOUR = "tour";
	private static final String DATE_TYPE_BOOKING = "booking";
	
	private TourBookingView filter;
	private String dateType;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public TourBookingViewSpecification(TourBookingView filter, String dateType, LocalDate startDate, LocalDate endDate) {
		super();
		this.filter = filter;
		this.dateType = dateType;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public Predicate toPredicate(Root<TourBookingView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(this.filter != null) {
			predicates.add(cb.equal(root.get("status"), Constant.STATUS_BOOKING_BOOKED));
			
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
		if(!ObjectUtils.isEmpty(dateType)) {
			if(startDate != null) {
				if(DATE_TYPE_TOUR.equalsIgnoreCase(dateType))
					predicates.add(cb.greaterThanOrEqualTo(root.get("tourDate"), startDate));
				else if(DATE_TYPE_BOOKING.equalsIgnoreCase(dateType))
					predicates.add(cb.greaterThanOrEqualTo(root.get("bookingDate"), startDate));
			}
			if(endDate != null) {
				if(DATE_TYPE_TOUR.equalsIgnoreCase(dateType))
					predicates.add(cb.lessThanOrEqualTo(root.get("tourDate"), endDate));
				else if(DATE_TYPE_BOOKING.equalsIgnoreCase(dateType))
					predicates.add(cb.lessThanOrEqualTo(root.get("bookingDate"), endDate));
			}
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

package com.r2dsolution.comein.spec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2dsolution.comein.entity.BookingInfo;

public class BookingInfoSpecification implements Specification<BookingInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingInfo filter;
	
	public BookingInfoSpecification(BookingInfo filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<BookingInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(this.filter != null) {
			Long hotelId = this.filter.getHotelId();
			String ownerId = this.filter.getOwnerId();
			String refName = this.filter.getRefName();
			String bookingNo = this.filter.getBookingNo();
			if(hotelId != null) {
				predicates.add(cb.equal(root.get("hotelId"), hotelId));
			}
			if(ownerId != null) {
				predicates.add(cb.equal(root.get("ownerId"), ownerId));
			}
			if(refName != null && !"".equals(refName)) {
				refName = genContainLikePattern(refName.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("refName")), refName));
			}
			if(bookingNo != null && !"".equals(bookingNo)) {
				bookingNo = genContainLikePattern(bookingNo.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("bookingNo")), bookingNo));
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

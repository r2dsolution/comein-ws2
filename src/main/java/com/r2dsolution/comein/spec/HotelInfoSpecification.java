package com.r2dsolution.comein.spec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2dsolution.comein.entity.HotelInfo;

public class HotelInfoSpecification implements Specification<HotelInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HotelInfo filter;
	
	public HotelInfoSpecification(HotelInfo filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<HotelInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(this.filter != null) {
			String email = this.filter.getEmail();
			String hotelName = this.filter.getHotelName();
			if(email != null && !"".equals(email)) {
				email = genContainLikePattern(email.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("email")), email));
			}
			if(hotelName != null && !"".equals(hotelName)) {
				hotelName = genContainLikePattern(hotelName.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("hotelName")), hotelName));
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

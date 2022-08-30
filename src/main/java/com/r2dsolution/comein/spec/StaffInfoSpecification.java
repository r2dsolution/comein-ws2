package com.r2dsolution.comein.spec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2dsolution.comein.entity.StaffInfo;

public class StaffInfoSpecification implements Specification<StaffInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StaffInfo filter;
	
	public StaffInfoSpecification(StaffInfo filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<StaffInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(this.filter != null) {
			Long hotelId = this.filter.getHotelId();
			String ownerId = this.filter.getOwnerId();
			String refName = this.filter.getRefName();
			if(hotelId != null) {
				predicates.add(cb.equal(root.get("hotelId"), hotelId));
			}
			if(ownerId != null) {
				predicates.add(cb.equal(root.get("ownerId"), ownerId));
			}
			if(refName != null && !"".equals(refName)) {
				refName = genContainLikePattern(refName.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("staffName")), refName));
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

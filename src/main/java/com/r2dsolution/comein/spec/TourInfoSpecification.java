package com.r2dsolution.comein.spec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.r2dsolution.comein.entity.TourInfo;

public class TourInfoSpecification implements Specification<TourInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TourInfo filter;
	
	public TourInfoSpecification(TourInfo filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<TourInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Collection<Predicate> predicates = new ArrayList<>();
		if(this.filter != null) {
			predicates.add(cb.equal(root.get("companyId"), filter.getCompanyId()));
			
			String tourName = this.filter.getTourName();
			if(tourName != null && !"".equals(tourName)) {
				tourName = genContainLikePattern(tourName.toUpperCase());
				predicates.add(cb.like(cb.upper(root.get("tourName")), tourName));
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

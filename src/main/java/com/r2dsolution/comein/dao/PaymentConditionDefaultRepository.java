package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PaymentConditionDefault;

public interface PaymentConditionDefaultRepository extends JpaSpecificationExecutor<PaymentConditionDefault>, PagingAndSortingRepository<PaymentConditionDefault, Long> {

	List<PaymentConditionDefault> findAll();
}


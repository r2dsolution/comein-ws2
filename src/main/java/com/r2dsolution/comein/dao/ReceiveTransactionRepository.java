package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.ReceiveTransaction;

public interface ReceiveTransactionRepository extends JpaSpecificationExecutor<ReceiveTransaction>, PagingAndSortingRepository<ReceiveTransaction, Long> {

}


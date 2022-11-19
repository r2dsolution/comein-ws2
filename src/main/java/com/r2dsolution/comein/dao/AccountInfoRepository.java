package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.AccountInfo;

public interface AccountInfoRepository extends JpaSpecificationExecutor<AccountInfo>, PagingAndSortingRepository<AccountInfo, Long> {

}


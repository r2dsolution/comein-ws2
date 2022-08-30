package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.PdpaInviteToken;

public interface PdpaInviteTokenRepository extends JpaSpecificationExecutor<PdpaInviteToken>, PagingAndSortingRepository<PdpaInviteToken, Long> {

	PdpaInviteToken findFirstByTokenAndStatus(String token, String status);
}


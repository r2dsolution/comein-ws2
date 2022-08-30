package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.r2dsolution.comein.entity.States;

@RepositoryRestResource(collectionResourceRel = "states", path = "states")
public interface StatesRepository extends JpaSpecificationExecutor<States>, PagingAndSortingRepository<States, Long> {

}


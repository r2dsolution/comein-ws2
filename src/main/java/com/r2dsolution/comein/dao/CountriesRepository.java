package com.r2dsolution.comein.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.r2dsolution.comein.entity.Countries;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countriesv2")
public interface CountriesRepository extends JpaSpecificationExecutor<Countries>, PagingAndSortingRepository<Countries, Long> {

}


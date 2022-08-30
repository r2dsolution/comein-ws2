package com.r2dsolution.comein.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.r2dsolution.comein.entity.Province;

public interface ProvinceRepository extends JpaSpecificationExecutor<Province>, PagingAndSortingRepository<Province, String> {

	List<Province> findByCountryCodeOrderByCode(String country);
}


package com.r2dsolution.comein.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.ProvinceRepository;
import com.r2dsolution.comein.dto.ProvinceDto;
import com.r2dsolution.comein.entity.Province;

@Service
public class ProvinceService {

	private static Logger log = LoggerFactory.getLogger(ProvinceService.class);
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	public List<ProvinceDto> getAllProvince(String country){
		log.info("Start getAllProvince with country : {}", country);
		
		List<ProvinceDto> results = new LinkedList<>();
			
		List<Province> provinces = provinceRepository.findByCountryCodeOrderByCode(country);
		
		ProvinceDto dto = null;
		for(Province entity : provinces) {
			dto = new ProvinceDto();
			dto.setCode(entity.getCode());
			dto.setNameEn(entity.getNameEn());
			dto.setCountry(entity.getCountryCode());
			
			results.add(dto);
		}
		
		return results;
	}
}

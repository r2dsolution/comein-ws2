package com.r2dsolution.comein.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.CountryRepository;
import com.r2dsolution.comein.dto.CountryDto;
import com.r2dsolution.comein.entity.Country;

@Service
public class CountryService {

	private static Logger log = LoggerFactory.getLogger(CountryService.class);
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<CountryDto> getAllCountry(){
		log.info("Start getAllCountry ");
		
		List<CountryDto> results = new LinkedList<>();
			
		Sort sort = Sort.by(Direction.ASC, "code");
		Iterable<Country> countries = countryRepository.findAll(sort);
		
		CountryDto dto = null;
		for(Country entity : countries) {
			dto = new CountryDto();
			dto.setCode(entity.getCode());
			dto.setNameEn(entity.getNameEn());
			
			results.add(dto);
		}
		
		return results;
	}
}

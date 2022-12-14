package com.r2dsolution.comein.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.TopUpRateCompanyRepository;
import com.r2dsolution.comein.dao.TopUpRateDefaultRepository;
import com.r2dsolution.comein.dto.TopUpRateDetailDto;
import com.r2dsolution.comein.dto.TopUpRateDto;
import com.r2dsolution.comein.entity.TopupRateCompany;
import com.r2dsolution.comein.entity.TopupRateDefault;
import com.r2dsolution.comein.exception.ServiceException;

@Service
public class TopUpRateService {

	private static Logger log = LoggerFactory.getLogger(TopUpRateService.class);
	
	@Autowired
	private TopUpRateDefaultRepository topUpRateDefaultRepository;
	
	@Autowired
	private TopUpRateCompanyRepository topUpRateCompanyRepository;
	
	public List<TopUpRateDetailDto> searchDefaultTopUpRate(){
		log.info("searchDefaultTopUpRate...");
		
		List<TopUpRateDetailDto> response = new ArrayList<>();
		List<TopupRateDefault> entities = topUpRateDefaultRepository.findAll();
		
		if(entities != null && !entities.isEmpty()) {
			TopUpRateDetailDto dto = null;
			for(TopupRateDefault entity : entities) {
				dto = new TopUpRateDetailDto();
				dto.setMinPeriod(entity.getMinPeriod());
				dto.setMaxPeriod(entity.getMaxPeriod());
				dto.setTopUpRate(entity.getTopupRate());
				dto.setComeinRate(entity.getComeinRate());
				dto.setHotelRate(entity.getHotelRate());
				dto.setUpdatedDate(entity.getUpdatedDate());
				
				response.add(dto);
			}
		} else {
			throw new ServiceException("Data not found.");
		}
		
		return response;
	}
	
	public TopUpRateDto searchCompanyTopUpRate(Long companyId){
		log.info("searchCompanyTopUpRate..companyId : {}.", companyId);
		
		List<TopupRateCompany> entities = topUpRateCompanyRepository.findByCompanyId(companyId);
		
		TopUpRateDto response = new TopUpRateDto();		
		TopUpRateDetailDto dto = null;
		List<TopUpRateDetailDto> details = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			TopupRateCompany e = entities.get(0);
			response.setCompanyId(e.getCompanyId());
			response.setUseDefault(e.getUseDefault());
			
			for(TopupRateCompany entity : entities) {
				dto = new TopUpRateDetailDto();
				dto.setMinPeriod(entity.getMinPeriod());
				dto.setMaxPeriod(entity.getMaxPeriod());
				dto.setTopUpRate(entity.getTopupRate());
				dto.setComeinRate(entity.getComeinRate());
				dto.setHotelRate(entity.getHotelRate());
				dto.setUpdatedDate(entity.getUpdatedDate());
				
				details.add(dto);
			}		
		} else {
			response.setCompanyId(companyId);
			response.setUseDefault(true);
			
			List<TopupRateDefault> entitieDefaults = topUpRateDefaultRepository.findAll();
			for(TopupRateDefault entity : entitieDefaults) {
				dto = new TopUpRateDetailDto();
				dto.setMinPeriod(entity.getMinPeriod());
				dto.setMaxPeriod(entity.getMaxPeriod());
				dto.setTopUpRate(entity.getTopupRate());
				dto.setComeinRate(entity.getComeinRate());
				dto.setHotelRate(entity.getHotelRate());
				dto.setUpdatedDate(entity.getUpdatedDate());
				
				details.add(dto);
			}
		}
		

		response.setDetail(details);
		
		return response;
	}
	
	public void saveDefaultTopUpRate(List<TopUpRateDetailDto> req, String userToken){
		log.info("Start saveDefaultTopUpRate...");
		
//		if(req != null) {
//			if(ObjectUtils.isEmpty(req.getCompanyId())) {
//				throw new ServiceException("Tour Company is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getTourName())) {
//				throw new ServiceException("Tour Name is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getStartDate())) {
//				throw new ServiceException("Start Date is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getEndDate())) {
//				throw new ServiceException("End Date is require.");	
//			}
//		}
		
		topUpRateDefaultRepository.deleteAll();
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		List<TopupRateDefault> entities = new ArrayList<>();
		TopupRateDefault entity = null;
		for(TopUpRateDetailDto dto : req) {
			entity = new TopupRateDefault();
			entity.setMinPeriod(dto.getMinPeriod());
			entity.setMaxPeriod(dto.getMaxPeriod());
			entity.setTopupRate(dto.getTopUpRate());
			entity.setComeinRate(dto.getComeinRate());
			entity.setHotelRate(dto.getHotelRate());
			entity.setUpdatedDate(currentTimestamp);
			
			entities.add(entity);
		}
		if(!entities.isEmpty())
			topUpRateDefaultRepository.saveAll(entities);
	}
	
	public void saveCompanyTopUpRate(Long companyId, TopUpRateDto req, String userToken){
		log.info("Start saveCompanyTopUpRate...companyId : {}", companyId);
		
//		if(req != null) {
//			if(ObjectUtils.isEmpty(req.getCompanyId())) {
//				throw new ServiceException("Tour Company is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getTourName())) {
//				throw new ServiceException("Tour Name is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getStartDate())) {
//				throw new ServiceException("Start Date is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getEndDate())) {
//				throw new ServiceException("End Date is require.");	
//			}
//		}
		topUpRateCompanyRepository.deleteByCompanyId(companyId);
		
		TopupRateCompany entity = null;
		List<TopupRateCompany> entities = new ArrayList<>();

		LocalDateTime currentTimestamp = LocalDateTime.now();
		boolean useDefault = req.isUseDefault();
		if(!useDefault) {
			for(TopUpRateDetailDto detail : req.getDetail()) {
				entity = new TopupRateCompany();
				entity.setCompanyId(companyId);
				entity.setUseDefault(useDefault);
				entity.setMinPeriod(detail.getMinPeriod());
				entity.setMaxPeriod(detail.getMaxPeriod());
				entity.setTopupRate(detail.getTopUpRate());
				entity.setComeinRate(detail.getComeinRate());
				entity.setHotelRate(detail.getHotelRate());
				entity.setUpdatedDate(currentTimestamp);
				
				entities.add(entity);
			}
			if(!entities.isEmpty())
				topUpRateCompanyRepository.saveAll(entities);
		}
	}

}

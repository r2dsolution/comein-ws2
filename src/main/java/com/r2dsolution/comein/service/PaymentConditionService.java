package com.r2dsolution.comein.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.PaymentConditionCompanyRepository;
import com.r2dsolution.comein.dao.PaymentConditionDefaultRepository;
import com.r2dsolution.comein.dto.PaymentConditionDto;
import com.r2dsolution.comein.entity.PaymentConditionCompany;
import com.r2dsolution.comein.entity.PaymentConditionDefault;
import com.r2dsolution.comein.exception.ServiceException;

@Service
public class PaymentConditionService {

	private static Logger log = LoggerFactory.getLogger(PaymentConditionService.class);
	
	@Autowired
	private PaymentConditionDefaultRepository paymentConditionDefaultRepository;
	
	@Autowired
	private PaymentConditionCompanyRepository paymentConditionCompanyRepository;
	
	public PaymentConditionDto searchDefaultPaymentCondition(){
		log.info("searchDefaultPaymentCondition...");
		
		PaymentConditionDto response = new PaymentConditionDto();
		List<PaymentConditionDefault> entities = paymentConditionDefaultRepository.findAll();
		
		if(entities != null && !entities.isEmpty()) {
			PaymentConditionDefault entity = entities.get(0);
			response.setPayableDay(entity.getPayableDay());
			response.setPayableTourDay(entity.getPayableTourDay());
		} else {
			throw new ServiceException("Data not found.");
		}
		
		return response;
	}
	
	public PaymentConditionDto searchCompanyPaymentCondition(Long companyId){
		log.info("searchCompanyPaymentCondition..companyId : {}.", companyId);
		
		List<PaymentConditionCompany> entities = paymentConditionCompanyRepository.findByCompanyId(companyId);
		
		PaymentConditionDto response = new PaymentConditionDto();		
		if(entities != null && !entities.isEmpty()) {
			PaymentConditionCompany entity = entities.get(0);
			response.setCompanyId(entity.getCompanyId());
			response.setUseDefault(entity.getUseDefault());
			response.setPayableDay(entity.getPayableDay());
			response.setPayableTourDay(entity.getPayableTourDay());
		} else {
			List<PaymentConditionDefault> entitieDefaults = paymentConditionDefaultRepository.findAll();
			
			if(entitieDefaults != null && !entitieDefaults.isEmpty()) {
				PaymentConditionDefault entity = entitieDefaults.get(0);
				response.setCompanyId(companyId);
				response.setUseDefault(true);
				response.setPayableDay(entity.getPayableDay());
				response.setPayableTourDay(entity.getPayableTourDay());
			}
		}
		
		return response;
	}
	
	public void saveDefaultPaymentCondition(PaymentConditionDto req, String userToken){
		log.info("Start saveDefaultPaymentCondition...");
		
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
		PaymentConditionDefault entity = null;
		List<PaymentConditionDefault> entities = paymentConditionDefaultRepository.findAll();
		if(entities != null && !entities.isEmpty()) {
			entity = entities.get(0);			
		} else {
			entity = new PaymentConditionDefault();
		}
		entity.setPayableDay(req.getPayableDay());
		entity.setPayableTourDay(req.getPayableTourDay());

		paymentConditionDefaultRepository.save(entity);
	}
	
	public void saveCompanyPaymentCondition(Long companyId, PaymentConditionDto req, String userToken){
		log.info("Start saveCompanyPaymentCondition...companyId : {}", companyId);
		
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
		paymentConditionCompanyRepository.deleteByCompanyId(companyId);
		
		boolean useDefault = req.isUseDefault();
		if(!useDefault) {
			PaymentConditionCompany entity = new PaymentConditionCompany();
			entity.setCompanyId(companyId);
			entity.setUseDefault(req.isUseDefault());
			entity.setPayableDay(req.getPayableDay());
			entity.setPayableTourDay(req.getPayableTourDay());
	
			paymentConditionCompanyRepository.save(entity);
		}
	}

}

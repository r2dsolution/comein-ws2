package com.r2dsolution.comein.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.r2dsolution.comein.util.ObjectUtils;

import com.r2dsolution.comein.cognito.AWSCognitoService;
import com.r2dsolution.comein.dao.TourCompanyRepository;
import com.r2dsolution.comein.dao.UserInfoRepository;
import com.r2dsolution.comein.dto.MailDto;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.TourCompanyDto;
import com.r2dsolution.comein.entity.TourCompany;
import com.r2dsolution.comein.entity.UserInfo;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.TourCompanySpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.EmailValidation;
import com.r2dsolution.comein.util.RandomString;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class TourCompanyService {

	private static Logger log = LoggerFactory.getLogger(TourCompanyService.class);
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private TourCompanyRepository tourCompanyRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AWSCognitoService cognitoService;
	
	public ResponseListDto<TourCompanyDto> searchTourCompany(String email, String companyName, Pageable pageable){
		System.out.println("searchTourCompany email : "+email+", companyName : "+companyName);;
		ResponseListDto<TourCompanyDto> response = new ResponseListDto<>();
		
		List<TourCompanyDto> results = new ArrayList<>();
		TourCompany filter = new TourCompany();
		filter.setEmail(email);
		filter.setCompanyName(companyName);
		TourCompanySpecification spec = new TourCompanySpecification(filter);
		
		Sort sort = Sort.by(Direction.DESC, "updatedDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<TourCompany> entities = tourCompanyRepository.findAll(spec, pageable1);
		
		TourCompanyDto dto = null;
		for(TourCompany entity : entities) {
			dto = new TourCompanyDto();
			dto.setId(entity.getId());
			dto.setCompanyName(entity.getCompanyName());
			dto.setTatNo(entity.getTatNo());
			dto.setBusinessName(entity.getBusinessName());
			dto.setReferenceName(entity.getReferenceName());
			dto.setEmail(entity.getEmail());
			dto.setMobileNo(entity.getMobileNo());
			dto.setStatus(entity.getStatus());
			dto.setOwnerId(entity.getOwnerId());
			
			results.add(dto);
		}
		
		response.setDatas(results);
		
		Pageable page = entities.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), entities.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public TourCompanyDto getTourCompany(long id){
		System.out.println("getTourCompany id : "+id);;
		TourCompanyDto response = null;
		
		Optional<TourCompany> entities = tourCompanyRepository.findById(id);
		if(entities.isPresent()) {
			
			TourCompany entity = entities.get();
		
			response = new TourCompanyDto();
			response.setId(entity.getId());
			response.setCompanyName(entity.getCompanyName());
			response.setTatNo(entity.getTatNo());
			response.setBusinessName(entity.getBusinessName());
			response.setReferenceName(entity.getReferenceName());
			response.setEmail(entity.getEmail());
			response.setMobileNo(entity.getMobileNo());
			response.setStatus(entity.getStatus());
			response.setOwnerId(entity.getOwnerId());
			response.setAddress(entity.getAddress());
			response.setCountry(entity.getCountry());
			response.setProvince(entity.getProvince());
			
			PersonalDto personalRes = null;
			PersonalDto personalReq = new PersonalDto();
			personalReq.setOwnerId(response.getOwnerId());
			List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
			if(!persons.isEmpty()) {
				personalRes = persons.get(0);
				response.setFirstName(personalRes.getFirstName());
				response.setLastName(personalRes.getLastName());
			}

		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public List<TourCompanyDto> saveTourCompany(TourCompanyDto req, String userToken){
		log.info("Start saveTourCompany companyName : {}", req.getCompanyName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getEmail())){
				throw new ServiceException("Email is require.");
			}
			if(!EmailValidation.patternMatches(req.getEmail())){
				throw new ServiceException("Email is invalid.");
			}
			if(ObjectUtils.isEmpty(req.getCompanyName())) {
				throw new ServiceException("Company Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getFirstName())) {
				throw new ServiceException("First Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getLastName())) {
				throw new ServiceException("Last Name is require.");	
			}
		}
		
		if(ObjectUtils.isEmpty(userToken)) userToken = Constant.USER_TOKEN_SELF;
		
		List<TourCompanyDto> results = new ArrayList<>();
		
		String refNo = new RandomString().nextString();
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		String tmpCognitoEmail = Constant.PREFIX_TEMP_COGNITO_EMAIL + req.getEmail();
		String ownerId = null;
		PersonalDto personalReq = new PersonalDto();
		personalReq.setEmail(tmpCognitoEmail);
		List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
		if(!persons.isEmpty()) {
			throw new ServiceException("Email is duplicate.");
		}
		personalReq = new PersonalDto();
		personalReq.setEmail(req.getEmail());
		persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
		if(!persons.isEmpty()) {
			throw new ServiceException("Email is duplicate.");
		}
		
		int cntHotel = tourCompanyRepository.countByCompanyNameContainingIgnoreCase(req.getCompanyName());
		if(cntHotel > 0) {
			throw new ServiceException("Company Name is duplicate.");
		}
		
		personalReq = new PersonalDto();
		personalReq.setFirstName(req.getFirstName());
		personalReq.setLastName(req.getLastName());
		if(StringUtils.isEmpty(req.getReferenceName()))
			personalReq.setReferenceName(StringUtils.trimToEmpty(req.getFirstName()) + " " + StringUtils.trimToEmpty(req.getLastName()));
		else
			personalReq.setReferenceName(req.getReferenceName());
		personalReq.setEmail(tmpCognitoEmail);
		personalReq.setMobileNo(req.getMobileNo());
		personalReq.setConsent(Constant.FLAG_N);
		ownerId = this.cognitoService.post(AWSCognitoService.POST_CREATE_USER, personalReq);			
		
		UserInfo userInfo = new UserInfo();
		userInfo.setRefNo(refNo);
		userInfo.setRole(Constant.ROLE_TOUR);
		userInfo.setInvitedDate(currentTimestamp);
		userInfo.setOwnerId(ownerId);
		userInfoRepository.save(userInfo);
		
		TourCompany entity = new TourCompany();
		entity.setCompanyName(req.getCompanyName());
		entity.setTatNo(req.getTatNo());
		entity.setBusinessName(req.getBusinessName());
		entity.setOwnerId(ownerId);
		entity.setReferenceName(req.getReferenceName());
		entity.setEmail(req.getEmail());
		entity.setMobileNo(req.getMobileNo());
		entity.setAddress(req.getAddress());
		entity.setCountry(req.getCountry());
		entity.setProvince(req.getProvince());
		entity.setRefNo(refNo);
		entity.setStatus(Constant.STATUS_UNVERIFY);
		entity.setCreatedDate(currentTimestamp);
		entity.setCreatedBy(userToken);
		entity.setUpdatedDate(currentTimestamp);
		entity.setUpdatedBy(userToken);
		
		tourCompanyRepository.save(entity);
		
		MailDto mailDto = new MailDto();
		mailDto.setRefNo(refNo);
		mailDto.setTo(req.getEmail());
		mailDto.setRole("admin");
		mailService.sendRegisterInvite(mailDto);
		
		return results;
	}
	
	public void verifyTourCompany(long id, String userToken){
		log.info("verifyTourCompany [verify] with id {}", id);
		
		Optional<TourCompany> entities = tourCompanyRepository.findById(id);
		if(entities.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
				
			TourCompany entity = entities.get();
			entity.setStatus(Constant.STATUS_VERIFY);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.tourCompanyRepository.save(entity);
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end verifyTourCompany [verify] with id {} successfully.", id);
		
	}
	
	public void unverifyTourCompany(long id, String userToken){
		log.info("unverifyTourCompany [unverify] with id {}", id);
		
		Optional<TourCompany> entities = tourCompanyRepository.findById(id);
		if(entities.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
				
			TourCompany entity = entities.get();
			entity.setStatus(Constant.STATUS_UNVERIFY);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.tourCompanyRepository.save(entity);
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end unverifyTourCompany [unverify] with id {} successfully.", id);
		
	}
	
	public List<TourCompanyDto> updateTourCompany(long id, TourCompanyDto req, String userToken){
		log.info("Start updateTourCompany id : {}, companyName : {}", id, req.getCompanyName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getCompanyName())) {
				throw new ServiceException("Company Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getFirstName())) {
				throw new ServiceException("First Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getLastName())) {
				throw new ServiceException("Last Name is require.");	
			}
		}
		
		List<TourCompanyDto> results = new ArrayList<>();
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		Optional<TourCompany> entities = tourCompanyRepository.findById(id);
		if(entities.isPresent()) {
			//update cognito
			PersonalDto personalRes = null;
			PersonalDto personalReq = new PersonalDto();
			personalReq.setEmail(req.getEmail());
			List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
			if(!persons.isEmpty()) {
				personalRes = persons.get(0);
				personalReq.setOwnerId(personalRes.getOwnerId());
				personalReq.setFirstName(req.getFirstName());
				personalReq.setLastName(req.getLastName());
				if(StringUtils.isEmpty(req.getReferenceName()))
					personalReq.setReferenceName(StringUtils.trimToEmpty(req.getFirstName()) + " " + StringUtils.trimToEmpty(req.getLastName()));
				else
					personalReq.setReferenceName(req.getReferenceName());
				personalReq.setEmail(req.getEmail());
				personalReq.setMobileNo(req.getMobileNo());
				this.cognitoService.post(AWSCognitoService.POST_UPDATE_USER, personalReq);
			}
			
			TourCompany entity = entities.get();
			entity.setCompanyName(req.getCompanyName());
			entity.setTatNo(req.getTatNo());
			entity.setBusinessName(req.getBusinessName());
			entity.setReferenceName(req.getReferenceName());
			entity.setMobileNo(req.getMobileNo());
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			entity.setMobileNo(req.getMobileNo());
			entity.setAddress(req.getAddress());
			entity.setCountry(req.getCountry());
			entity.setProvince(req.getProvince());
			
			tourCompanyRepository.save(entity);
		}
		
		return results;
	}
	
	public void updateTourCompanyProfile(TourCompanyDto req, String userToken){
		log.info("Start updateTourCompanyProfile id : {}", req.getId());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getFirstName())) {
				throw new ServiceException("First Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getLastName())) {
				throw new ServiceException("Last Name is require.");	
			}
		}
		
		//update cognito
		PersonalDto personalRes = null;
		PersonalDto personalReq = new PersonalDto();
		personalReq.setOwnerId(userToken);
		List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
		if(!persons.isEmpty()) {
			personalRes = persons.get(0);
			personalReq.setOwnerId(personalRes.getOwnerId());
			personalReq.setFirstName(req.getFirstName());
			personalReq.setLastName(req.getLastName());
			if(StringUtils.isEmpty(req.getReferenceName()))
				personalReq.setReferenceName(StringUtils.trimToEmpty(req.getFirstName()) + " " + StringUtils.trimToEmpty(req.getLastName()));
			else
				personalReq.setReferenceName(req.getReferenceName());
			this.cognitoService.post(AWSCognitoService.POST_UPDATE_USER, personalReq);
		}
	}

}

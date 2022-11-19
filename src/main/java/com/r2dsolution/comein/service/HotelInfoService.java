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
import com.r2dsolution.comein.dao.HotelInfoRepository;
import com.r2dsolution.comein.dao.UserInfoRepository;
import com.r2dsolution.comein.dto.HotelAdminDto;
import com.r2dsolution.comein.dto.HotelNameDto;
import com.r2dsolution.comein.dto.MailDto;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.entity.HotelInfo;
import com.r2dsolution.comein.entity.UserInfo;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.HotelInfoSpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.EmailValidation;
import com.r2dsolution.comein.util.RandomString;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class HotelInfoService {

	private static Logger log = LoggerFactory.getLogger(HotelInfoService.class);
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private HotelInfoRepository hotelInfoRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AWSCognitoService cognitoService;
	
	public ResponseListDto<HotelAdminDto> searchHotelInfo(String email, String hotelName, Pageable pageable){
		System.out.println("searchHotelInfo email : "+email+", hotelName : "+hotelName);;
		ResponseListDto<HotelAdminDto> response = new ResponseListDto<>();
		
		List<HotelAdminDto> results = new ArrayList<>();
		HotelInfo filter = new HotelInfo();
		filter.setEmail(email);
		filter.setHotelName(hotelName);
		HotelInfoSpecification spec = new HotelInfoSpecification(filter);
		
		Sort sort = Sort.by(Direction.DESC, "updatedDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<HotelInfo> hotels = hotelInfoRepository.findAll(spec, pageable1);
		
		HotelAdminDto dto = null;
		for(HotelInfo entity : hotels) {
			dto = new HotelAdminDto();
			dto.setId(entity.getId());
			dto.setAddress(entity.getAddress());
			dto.setOwnerId(entity.getOwnerId());
			dto.setReferenceName(entity.getReferenceName());
			dto.setEmail(entity.getEmail());
			dto.setHotelName(entity.getHotelName());
			dto.setMobileNo(entity.getMobileNo());
			dto.setStatus(entity.getStatus());
			
			results.add(dto);
		}
		
		response.setDatas(results);
		
		Pageable page = hotels.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), hotels.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public HotelAdminDto getHotelInfo(long id){
		System.out.println("getHotelInfo id : "+id);;
		HotelAdminDto response = null;
		
		Optional<HotelInfo> hotels = hotelInfoRepository.findById(id);
		if(hotels.isPresent()) {
			
			HotelInfo entity = hotels.get();
		
			response = new HotelAdminDto();
			response.setId(entity.getId());
			response.setAddress(entity.getAddress());
			response.setOwnerId(entity.getOwnerId());
			response.setReferenceName(entity.getReferenceName());
			response.setEmail(entity.getEmail());
			response.setHotelName(entity.getHotelName());
			response.setMobileNo(entity.getMobileNo());
			response.setStatus(entity.getStatus());
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
	
	public List<HotelAdminDto> saveHotelAdmin(HotelAdminDto req, String userToken){
		log.info("Start saveHotelAdmin hotelName : {}", req.getHotelName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getEmail())){
				throw new ServiceException("Email is require.");
			}
			if(!EmailValidation.patternMatches(req.getEmail())){
				throw new ServiceException("Email is invalid.");
			}
			if(ObjectUtils.isEmpty(req.getHotelName())) {
				throw new ServiceException("Hotel Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getFirstName())) {
				throw new ServiceException("First Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getLastName())) {
				throw new ServiceException("Last Name is require.");	
			}
		}
		
		if(ObjectUtils.isEmpty(userToken)) userToken = Constant.USER_TOKEN_SELF;
		
		List<HotelAdminDto> results = new ArrayList<>();
		
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
		userInfo.setRole(Constant.ROLE_HOTEL_ADMIN);
		userInfo.setInvitedDate(currentTimestamp);
		userInfo.setOwnerId(ownerId);
		userInfoRepository.save(userInfo);
		
		HotelInfo entity = new HotelInfo();
		entity.setHotelName(req.getHotelName());
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
		
		hotelInfoRepository.save(entity);
		
		MailDto mailDto = new MailDto();
		mailDto.setRefNo(refNo);
		mailDto.setTo(req.getEmail());
		mailDto.setRole("admin");
		mailService.sendRegisterInvite(mailDto);
		
		return results;
	}
	
	public void verifyHotelAdmin(long id, String userToken){
		log.info("verifyHotelAdmin [verify] with id {}", id);
		
		Optional<HotelInfo> hotels = hotelInfoRepository.findById(id);
		if(hotels.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
				
			HotelInfo entity = hotels.get();
			entity.setStatus(Constant.STATUS_VERIFY);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.hotelInfoRepository.save(entity);
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end verifyHotelAdmin [verify] with id {} successfully.", id);
		
	}
	
	public void unverifyHotelAdmin(long id, String userToken){
		log.info("unverifyHotelAdmin [unverify] with id {}", id);
		
		Optional<HotelInfo> hotels = hotelInfoRepository.findById(id);
		if(hotels.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
				
			HotelInfo entity = hotels.get();
			entity.setStatus(Constant.STATUS_UNVERIFY);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.hotelInfoRepository.save(entity);
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end disableHotelAdmin [unverify] with id {} successfully.", id);
		
	}
	
	
	public List<HotelNameDto> listAllHotelName(){
		System.out.println("listAllHotelName ");;
		List<HotelNameDto> response = new ArrayList<>();
		
		List<HotelInfo> hotels = hotelInfoRepository.findByStatus(Constant.STATUS_VERIFY);
		if(!hotels.isEmpty()) {
			
			HotelNameDto dto = null;
			for(HotelInfo entity : hotels) {
				dto = new HotelNameDto();
				
				dto.setId(entity.getId());
				dto.setHotelName(entity.getHotelName());
				
				response.add(dto);
			}
			
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
}

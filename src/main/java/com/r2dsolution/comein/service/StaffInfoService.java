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
import com.r2dsolution.comein.dao.StaffInfoRepository;
import com.r2dsolution.comein.dao.UserInfoRepository;
import com.r2dsolution.comein.dto.MailDto;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.StaffDto;
import com.r2dsolution.comein.entity.HotelInfo;
import com.r2dsolution.comein.entity.StaffInfo;
import com.r2dsolution.comein.entity.UserInfo;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.StaffInfoSpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.EmailValidation;
import com.r2dsolution.comein.util.RandomString;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class StaffInfoService {

	private static Logger log = LoggerFactory.getLogger(StaffInfoService.class);
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private StaffInfoRepository staffInfoRepository;
	
	@Autowired
	private HotelInfoRepository hotelInfoRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AWSCognitoService cognitoService;
	
	public ResponseListDto<StaffDto> searchStaffInfo(String userToken, String email, String refName, Pageable pageable){
		log.info("searchStaffInfo email : {}, refName : {}", email, refName);
		
		ResponseListDto<StaffDto> response = new ResponseListDto<>();
		
		List<StaffDto> results = new ArrayList<>();
		
		UserInfo userInfo = userInfoRepository.findFirstByUserTokenAndRoleAndStatus(userToken, Constant.ROLE_HOTEL_ADMIN, Constant.STATUS_ACTIVE);
		if(userInfo == null) {
			throw new ServiceException("Cannot found user infomation by user token.");
		}
		
		String refNo = userInfo.getRefNo();
			
		HotelInfo hotelInfo = hotelInfoRepository.findByRefNo(refNo);
		if(hotelInfo == null) {
			throw new ServiceException("Cannot found user infomation by ref no.");
		}
		
		Long hotelId = hotelInfo.getId();
		
		PersonalDto personalRes = null;
		if(!StringUtils.isEmpty(email)) {
			PersonalDto personalReq = new PersonalDto();
			personalReq.setEmail(email);
			List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
			if(!persons.isEmpty()) {
				personalRes = persons.get(0);
			} else {
//				throw new ServiceException("Data not found.");
				response.setDatas(results);
				
				PaggingDto pagging = new PaggingDto(0, 10, 0);
				response.setPagging(pagging);
				return response;
			}
		}
		
		StaffInfo filter = new StaffInfo();
		filter.setHotelId(hotelId);
		
		if(personalRes != null) {
			filter.setOwnerId(personalRes.getOwnerId());
		}
		filter.setRefName(refName);
		StaffInfoSpecification spec = new StaffInfoSpecification(filter);

		Sort sort = Sort.by(Direction.DESC, "updatedDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<StaffInfo> staffs = staffInfoRepository.findAll(spec, pageable1);
		
		StaffDto dto = null;
		for(StaffInfo entity : staffs) {
			dto = new StaffDto();
			dto.setId(entity.getId());
			dto.setOwnerId(entity.getOwnerId());
			dto.setReferenceName(entity.getRefName());
			dto.setStatus(entity.getStatus());
			
			results.add(dto);
		}
		
		response.setDatas(results);
		
		Pageable page = staffs.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), staffs.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public StaffDto getHotelStaffInfo(long id){
		log.info("getHotelStaffInfo , id : {}", id);
		
		StaffDto response = null;
		
		Optional<StaffInfo> hotels = staffInfoRepository.findById(id);
		if(hotels.isPresent()) {
			
			StaffInfo entity = hotels.get();
		
			response = new StaffDto();
			response.setId(entity.getId());
			response.setOwnerId(entity.getOwnerId());
			response.setReferenceName(entity.getRefName());
			response.setStatus(entity.getStatus());
			
			PersonalDto personalRes = null;
			PersonalDto personalReq = new PersonalDto();
			personalReq.setOwnerId(response.getOwnerId());
			List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
			if(!persons.isEmpty()) {
				personalRes = persons.get(0);
				response.setFirstName(personalRes.getFirstName());
				response.setLastName(personalRes.getLastName());
				response.setMobileNo(personalRes.getMobileNo());
				response.setEmail(personalRes.getEmail());
			}

		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public List<StaffDto> saveHotelStaff(StaffDto req, String userToken){
		log.info("Start saveHotelStaff referenceName : {}", req.getReferenceName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getEmail())){
				throw new ServiceException("Email is require.");
			}
			if(!EmailValidation.patternMatches(req.getEmail())){
				throw new ServiceException("Email is invalid.");
			}
			if(ObjectUtils.isEmpty(req.getReferenceName())) {
				throw new ServiceException("Reference Name is require.");	
			}
		}
		
		UserInfo user = this.userInfoRepository.findFirstByUserTokenAndStatus(userToken, Constant.STATUS_ACTIVE);
		if(user == null) {
			throw new ServiceException("Cannot create Hotel Staff, User not found");
		}
		HotelInfo hotel = this.hotelInfoRepository.findByRefNo(user.getRefNo());
		if(hotel == null) {
			throw new ServiceException("Cannot create Hotel Staff, Hotel Info not found.");
		}
		Long hotelId = hotel.getId();
		
		List<StaffDto> results = new ArrayList<>();

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
		personalReq.setMobileNo(Constant.TEMP_STAFF_PHONE);
		personalReq.setConsent(Constant.FLAG_N);
		ownerId = this.cognitoService.post(AWSCognitoService.POST_CREATE_USER, personalReq);			
		
		UserInfo userInfo = new UserInfo();
		userInfo.setRefNo(refNo);
		userInfo.setRole(Constant.ROLE_HOTEL_STAFF);
		userInfo.setInvitedDate(currentTimestamp);
		userInfo.setOwnerId(ownerId);
		userInfoRepository.save(userInfo);
		
		StaffInfo entity = new StaffInfo();
		entity.setHotelId(hotelId);
		entity.setRefNo(refNo);
		entity.setOwnerId(ownerId);
		entity.setRefName(req.getReferenceName());
		entity.setStatus(Constant.STATUS_VERIFY);
		entity.setCreatedDate(currentTimestamp);
		entity.setCreatedBy(userToken);
		entity.setUpdatedDate(currentTimestamp);
		entity.setUpdatedBy(userToken);
		
		staffInfoRepository.save(entity);
		
		MailDto mailDto = new MailDto();
		mailDto.setRefNo(refNo);
		mailDto.setTo(req.getEmail());
		mailDto.setRole("staff");
		mailService.sendRegisterInvite(mailDto);
		
		return results;
	}
	
	public void verifyHotelStaff(long id, String userToken){
		log.info("verifyHotelStaff [verify] with id {}", id);
		
		Optional<StaffInfo> staffs = this.staffInfoRepository.findById(id);
		if(staffs.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
				
			StaffInfo entity = staffs.get();
			entity.setStatus(Constant.STATUS_VERIFY);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.staffInfoRepository.save(entity);
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end verifyHotelStaff [verify] with id {} successfully.", id);
		
	}
	
	public void unverifyHotelStaff(long id, String userToken){
		log.info("unverifyHotelStaff [disable] with id {}", id);
		
		Optional<StaffInfo> staffs = this.staffInfoRepository.findById(id);
		if(staffs.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
				
			StaffInfo entity = staffs.get();
			entity.setStatus(Constant.STATUS_UNVERIFY);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.staffInfoRepository.save(entity);
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end unverifyHotelStaff [unverify] with id {} successfully.", id);
		
	}
}

package com.r2dsolution.comein.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.cognito.AWSCognitoService;
import com.r2dsolution.comein.dao.BookingKycRepository;
import com.r2dsolution.comein.dao.BookingRepository;
import com.r2dsolution.comein.dao.PdpaInviteTokenRepository;
import com.r2dsolution.comein.dao.UserInfoRepository;
import com.r2dsolution.comein.dto.PersonalConsentDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.entity.BookingKyc;
import com.r2dsolution.comein.entity.PdpaInviteToken;
import com.r2dsolution.comein.entity.UserInfo;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.ObjectUtils;
import com.r2dsolution.comein.util.StringUtils;


@Service
public class PersonalService {

	private static Logger log = LoggerFactory.getLogger(PersonalService.class);

	@Autowired
	private AWSCognitoService cognitoService;
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private PdpaInviteTokenRepository pdpaInviteTokenRepository;
	
	@Autowired
	private BookingKycRepository bookingKycRepository;
	
	public PersonalDto getPersonalInfo(String ownerId){
		log.info("getPersonalInfo ownerId : {}", ownerId);;
		PersonalDto response = null;
		
		PersonalDto personalDto = new PersonalDto();
		personalDto.setOwnerId(ownerId);
		List<PersonalDto> results = this.cognitoService.get(AWSCognitoService.GET_USER, personalDto);
		if(!results.isEmpty()) {
			personalDto = results.get(0);
			
			response = new PersonalDto();
			
			String email = null;
			UserInfo user = this.userInfoRepository.findFirstByOwnerId(ownerId);
			if(user != null) {
				String role = user.getRole();
				if(Constant.ROLE_HOTEL_ADMIN.equals(role)){
					response.setFirstName(StringUtils.getValueOrDefault(personalDto.getFirstName(), Constant.DASH));
					response.setLastName(StringUtils.getValueOrDefault(personalDto.getLastName(), Constant.DASH));
					response.setReferenceName(personalDto.getReferenceName());
				} else if(Constant.ROLE_HOTEL_STAFF.equals(role)){
					email = StringUtils.getValueOrDefault(personalDto.getEmail(), Constant.DASH);
					email = email.replaceFirst(Constant.PREFIX_TEMP_COGNITO_EMAIL, "");
					response.setFirstName(StringUtils.getValueOrDefault(personalDto.getFirstName(), Constant.DASH));
					response.setLastName(StringUtils.getValueOrDefault(personalDto.getLastName(), Constant.DASH));
					response.setEmail(email);
					response.setReferenceName(personalDto.getReferenceName());
				} else if(Constant.ROLE_TOUR.equals(role)){
					response.setFirstName(StringUtils.getValueOrDefault(personalDto.getFirstName(), Constant.DASH));
					response.setLastName(StringUtils.getValueOrDefault(personalDto.getLastName(), Constant.DASH));
					response.setReferenceName(personalDto.getReferenceName());
				}
			} else {
				int cntBooking = this.bookingRepository.countByOwnerId(ownerId);
				if(cntBooking > 0) {
					email = StringUtils.getValueOrDefault(personalDto.getEmail(), Constant.DASH);
					email = email.replaceFirst(Constant.PREFIX_TEMP_COGNITO_EMAIL, "");
					response.setFirstName(StringUtils.getValueOrDefault(personalDto.getFirstName(), Constant.DASH));
					response.setLastName(StringUtils.getValueOrDefault(personalDto.getLastName(), Constant.DASH));
					response.setEmail(email);
					response.setReferenceName(personalDto.getReferenceName());	
				}
			}
			
		} else {
			throw new ServiceException("Personal information not found.");
		}

		return response;
	}
	
	public void updatePersonalInfo(PersonalDto req, String userToken){
		log.info("Start updatePersonalInfo... ");
		
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
	
	public PersonalDto getPersonalConsent(PersonalConsentDto req){
		log.info("getPersonalConsent token : {}", req.getToken());
		PersonalDto response = new PersonalDto();
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getToken())){
				throw new ServiceException("Token is require.");
			}
			if(ObjectUtils.isEmpty(req.getSecretCode())) {
				throw new ServiceException("Secret Code is require.");	
			}
		}
		
		PdpaInviteToken pdpaToken = this.pdpaInviteTokenRepository.findFirstByTokenAndStatus(req.getToken(), Constant.STATUS_ACTIVE.toUpperCase());
		if(pdpaToken == null) throw new ServiceException("Token not found.");

		BookingKyc bookingKyc = this.bookingKycRepository.findFirstByRefId(pdpaToken.getComeinId());
		if(bookingKyc == null) throw new ServiceException("Booking KYC not found.");
		
		log.debug("token : {}, comeinId : {}", pdpaToken.getToken(), pdpaToken.getComeinId());
		log.debug("req.secretCode : {}, db.secretCode : {}", req.getSecretCode(), pdpaToken.getSecretCode());
		log.debug("expireDate : {}, now : {}, isBefore : {}", pdpaToken.getExpireDate(), LocalDateTime.now(), pdpaToken.getExpireDate().isBefore(LocalDateTime.now()));
		
		if(ObjectUtils.isEmpty(pdpaToken.getExpireDate())) throw new ServiceException("Expire Date not found.");
		if(ObjectUtils.isEmpty(pdpaToken.getComeinId())) throw new ServiceException("Token not found.");
		if(ObjectUtils.isEmpty(pdpaToken.getSecretCode())) throw new ServiceException("Secret Code not found.");
		if(pdpaToken.getExpireDate().isBefore(LocalDateTime.now())) throw new ServiceException("Token is Expired.");
		if(!pdpaToken.getSecretCode().equals(req.getSecretCode())) throw new ServiceException("Token is Invalid.");
		
		PersonalDto personalDto = new PersonalDto();
		personalDto.setComeinId(pdpaToken.getComeinId());
		List<PersonalDto> results = this.cognitoService.get(AWSCognitoService.GET_USER, personalDto);
		if(!results.isEmpty()) {
			personalDto = results.get(0);
			
			response.setConsentId(bookingKyc.getId());		//booking_kyc.id
			response.setIdNo(personalDto.getIdNo());
			response.setFirstName(personalDto.getFirstName());
			response.setLastName(personalDto.getLastName());
			response.setBirthDate(personalDto.getBirthDate());
			response.setIssueDate(personalDto.getIssueDate());
			response.setExpireDate(personalDto.getExpireDate());
			
			response.setReferenceName(personalDto.getReferenceName());
			response.setConsent(bookingKyc.getConsent());
			if(Constant.FLAG_Y.equals(bookingKyc.getConsent())) response.setStatus(Constant.STATUS_CONSENT_CONFIRM);
			else response.setStatus(Constant.STATUS_CONSENT_INVALIDCONFIRM);		
		}
		
		return response;
	}
	
}

package com.r2dsolution.comein.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2dsolution.comein.cognito.AWSCognitoService;
import com.r2dsolution.comein.dao.HotelInfoRepository;
import com.r2dsolution.comein.dao.StaffInfoRepository;
import com.r2dsolution.comein.dao.TourCompanyRepository;
import com.r2dsolution.comein.dao.UserInfoRepository;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.UserDto;
import com.r2dsolution.comein.dto.UserPasswordDto;
import com.r2dsolution.comein.entity.HotelInfo;
import com.r2dsolution.comein.entity.StaffInfo;
import com.r2dsolution.comein.entity.TourCompany;
import com.r2dsolution.comein.entity.UserInfo;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class UserInfoService {

	private static Logger log = LoggerFactory.getLogger(UserInfoService.class);
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private HotelInfoRepository hotelInfoRepository;
	
	@Autowired
	private StaffInfoRepository staffInfoRepository;
	
	@Autowired
	private TourCompanyRepository tourCompanyRepository;
	
	@Autowired
	private AWSCognitoService cognitoService;
	
	public UserDto getUserInfoByRefNo(String refNo){
		log.info("getUserInfoByRefNo refNo : {}", refNo);
		UserDto response = null;
		
		UserInfo entity = userInfoRepository.findFirstByRefNoAndStatusNull(refNo);
		log.info("getUserInfoByRefNo entity : {}", entity);
		if(entity != null) {
			
			PersonalDto personalTmpRes = null;
			PersonalDto personalTmpReq = new PersonalDto();
			personalTmpReq.setOwnerId(entity.getOwnerId());
			List<PersonalDto> personTmps = this.cognitoService.get(AWSCognitoService.GET_USER, personalTmpReq);
			if(!personTmps.isEmpty()) {
				personalTmpRes = personTmps.get(0);
				String email = personalTmpRes.getEmail();
				email = email.replaceFirst(Constant.PREFIX_TEMP_COGNITO_EMAIL, "");

				response = new UserDto();
				
				response.setFirstName(personalTmpRes.getFirstName());
				response.setLastName(personalTmpRes.getLastName());
				response.setEmail(email);
				response.setRole(entity.getRole());
			}
			
		} else {
			throw new ServiceException("Cannot found user infomation by ref no.");
		}

		return response;
	}
	
	public UserDto getUserInfoByToken(String userToken){
		log.info("getUserInfoByToken userToken : {}", userToken);;
		UserDto response = null;
		
		try {
			UserInfo entity = userInfoRepository.findFirstByUserTokenAndStatus(userToken, "Active");
			if(entity != null) {
				String email = null;
				PersonalDto personalTmpRes = null;
				PersonalDto personalTmpReq = new PersonalDto();
				personalTmpReq.setOwnerId(entity.getOwnerId());
				List<PersonalDto> personTmps = this.cognitoService.get(AWSCognitoService.GET_USER, personalTmpReq);
				if(!personTmps.isEmpty()) {
					personalTmpRes = personTmps.get(0);
					email = personalTmpRes.getEmail();
				}
				
				String refNo = entity.getRefNo();
				response = new UserDto();
				
				response.setEmail(email);
				response.setRole(entity.getRole());
				
				Long hotelId = null;
				Long tourId = null;
				String name = null;
				String hotelName = null;
				if(Constant.ROLE_HOTEL_ADMIN.equals(entity.getRole())){
					HotelInfo hotelInfo = hotelInfoRepository.findByRefNo(refNo);
					if(hotelInfo != null) {
						name = hotelInfo.getReferenceName();
						hotelId = hotelInfo.getId();
						hotelName = hotelInfo.getHotelName();
					}
				} else if(Constant.ROLE_HOTEL_STAFF.equals(entity.getRole())){
					StaffInfo staffInfo = staffInfoRepository.findByRefNo(refNo);
					if(staffInfo != null) {
						Optional<HotelInfo> hotelInfo = hotelInfoRepository.findById(staffInfo.getHotelId());
						name = staffInfo.getRefName();
						hotelId = staffInfo.getHotelId();
						hotelName = hotelInfo.get().getHotelName();
					}
				} else if(Constant.ROLE_TOUR.equals(entity.getRole())){
					TourCompany tourCompany = tourCompanyRepository.findByRefNo(refNo);
					if(tourCompany != null) {
						if(ObjectUtils.isEmpty(name))
							name = tourCompany.getReferenceName();
						tourId = tourCompany.getId();
						hotelName = tourCompany.getCompanyName();
					}
				}
				
				response.setHotelId(hotelId);
				response.setTourId(tourId);
				response.setName(name);
				response.setHotelName(hotelName);
				
				
				
			} else {
				throw new ServiceException("Cannot found user infomation by user token.");
			}
		}catch(ServiceException e) {
			throw e;
		}catch(Exception e) {
			throw new ServiceException("Unknown error.");
		}

		return response;
	}
	
	public List<String> getUserRole(String userToken){
		log.info("getUserInfo userToken : {}", userToken);;
		List<String> roles = null;
		
		List<UserInfo> users = userInfoRepository.findByUserTokenAndStatus(userToken, "Active");
		if(!users.isEmpty()) {
			roles = new ArrayList<>();
			
			String role = null;
			for(UserInfo entity : users) {
				role = entity.getRole();
				roles.add(role);
//				if(Constant.ROLE_HOTEL_ADMIN.equals(role)) {
//					HotelInfo info = this.hotelInfoRepository.findFirstByOwnerId(entity.getOwnerId());
//					if(info != null && !ObjectUtils.isEmpty(info.getStatus()) && Constant.STATUS_VERIFY.equals(info.getStatus())) {
//						roles.add(Constant.ROLE_HOTEL_ADMIN_VERIFY);
//					}
//				} else if(Constant.ROLE_HOTEL_STAFF.equals(role)) {
//					StaffInfo info = this.staffInfoRepository.findFirstByOwnerId(entity.getOwnerId());
//					if(info != null && !ObjectUtils.isEmpty(info.getStatus()) && Constant.STATUS_VERIFY.equals(info.getStatus())) {
//						roles.add(Constant.ROLE_HOTEL_STAFF_VERIFY);
//					}
//				} else if(Constant.ROLE_TOUR.equals(role)) {
//					TourCompany info = this.tourCompanyRepository.findFirstByOwnerId(entity.getOwnerId());
//					if(info != null && !ObjectUtils.isEmpty(info.getStatus()) && Constant.STATUS_VERIFY.equals(info.getStatus())) {
//						roles.add(Constant.ROLE_TOUR_VERIFY);
//					}
//				}
			}
		} else {
			throw new ServiceException("Cannot found user role by user token.");
		}

		return roles;
	}
	
	public List<String> getUserRolePermission(String userToken, String role){
		log.info("getUserRolePermission userToken : {}, role : {}", userToken, role);;
		List<String> permissions = null;
		
		UserInfo entity = userInfoRepository.findFirstByUserTokenAndRoleAndStatus(userToken, role, "Active");
		if(entity != null) {
			permissions = new ArrayList<>();
			if (Constant.ROLE_COMEIN_ADMIN.equals(role)) {
				permissions.add(Constant.PERMISSION_ACCESS_MENU+"HotelAdmin");
				permissions.add(Constant.PERMISSION_ACCESS_MENU+"TourAdmin");
				permissions.add(Constant.PERMISSION_ACCESS_MENU+"OTA");
			} else if (Constant.ROLE_HOTEL_ADMIN.equals(role)) {
				HotelInfo info = this.hotelInfoRepository.findFirstByOwnerId(entity.getOwnerId());
				if (info != null && !ObjectUtils.isEmpty(info.getStatus())
						&& Constant.STATUS_VERIFY.equals(info.getStatus())) {
					permissions.add(Constant.PERMISSION_ACCESS_MENU+"HotelStaff");
				}
			} else if (Constant.ROLE_HOTEL_STAFF.equals(role)) {
				StaffInfo info = this.staffInfoRepository.findFirstByOwnerId(entity.getOwnerId());
				if (info != null && !ObjectUtils.isEmpty(info.getStatus())
						&& Constant.STATUS_VERIFY.equals(info.getStatus())) {
					permissions.add(Constant.PERMISSION_ACCESS_MENU+"Booking");
				}
			} else if (Constant.ROLE_TOUR.equals(role)) {
				TourCompany info = this.tourCompanyRepository.findFirstByOwnerId(entity.getOwnerId());
				if (info != null && !ObjectUtils.isEmpty(info.getStatus())
						&& Constant.STATUS_VERIFY.equals(info.getStatus())) {
					permissions.add(Constant.PERMISSION_ACCESS_MENU+"TourCompany");
					permissions.add(Constant.PERMISSION_ACCESS_MENU+"Tour");
					permissions.add(Constant.PERMISSION_ACCESS_MENU+"TourInventory");
					permissions.add(Constant.PERMISSION_ACCESS_MENU+"TourBooking");
				}
			}
		} else {
			throw new ServiceException("Cannot found user role by user token.");
		}

		return permissions;
	}
	
	
	public void enableUser(UserDto req){
		log.info("enableUser [enable] with refNo {}", req.getRefNo());
		
		UserInfo entity = userInfoRepository.findFirstByRefNoAndStatusNull(req.getRefNo());
		if(entity != null) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
		
			String role = entity.getRole();
			
			String tmpCognitoEmail = Constant.PREFIX_TEMP_COGNITO_EMAIL + req.getEmail();
			String tmpOwnerId = null;
			String ownerId = null;
			PersonalDto personalTmpRes = null;
			PersonalDto personalTmpReq = new PersonalDto();
			personalTmpReq.setEmail(tmpCognitoEmail);
			List<PersonalDto> personTmps = this.cognitoService.get(AWSCognitoService.GET_USER, personalTmpReq);
			if(!personTmps.isEmpty()) {
				personalTmpRes = personTmps.get(0);
				tmpOwnerId = personalTmpRes.getOwnerId();
				
				//update cognito
				PersonalDto personalRes = null;
				PersonalDto personalReq = new PersonalDto();
				personalReq.setEmail(req.getEmail());
				List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
				if(!personTmps.isEmpty()) {
					personalRes = persons.get(0);
					personalReq.setOwnerId(personalRes.getOwnerId());
					personalReq.setFirstName(personalTmpRes.getFirstName());
					personalReq.setLastName(personalTmpRes.getLastName());
					if(StringUtils.isEmpty(personalTmpRes.getReferenceName()))
						personalReq.setReferenceName(StringUtils.trimToEmpty(personalTmpRes.getFirstName()) + " " + StringUtils.trimToEmpty(personalTmpRes.getLastName()));
					else
						personalReq.setReferenceName(personalTmpRes.getReferenceName());
					personalReq.setEmail(req.getEmail());
					personalReq.setMobileNo(personalTmpRes.getMobileNo());
					ownerId = this.cognitoService.post(AWSCognitoService.POST_UPDATE_USER, personalReq);
					
					//delete temp
					this.cognitoService.delete(tmpCognitoEmail);
					
					//update ownerId temp to real
					int cnt = 0;
					if(Constant.ROLE_HOTEL_ADMIN.equals(role)) {
						cnt = this.hotelInfoRepository.updateOwnerIdByOwnerId(ownerId, tmpOwnerId);
					} else if(Constant.ROLE_HOTEL_STAFF.equals(role)) {
						cnt = this.staffInfoRepository.updateOwnerIdByOwnerId(ownerId, tmpOwnerId);
					} else if(Constant.ROLE_TOUR.equals(role)) {
						cnt = this.tourCompanyRepository.updateOwnerIdByOwnerId(ownerId, tmpOwnerId);
					}
					log.info("update hotelAdmin ownerId from : {} to : {} result : {}.", tmpOwnerId, ownerId, cnt);
					
					//update user info
					entity.setOwnerId(ownerId);
					entity.setUserToken(req.getUserToken());
					entity.setRegisteredDate(currentTimestamp);
					entity.setStatus("Active");
					
					this.userInfoRepository.save(entity);
				}
			}
			
		} else {
			throw new ServiceException("Cannot update data, Data not found.");
		}
		log.info("end enableUser [enable] with refNo {} successfully.", req.getRefNo());
		
	}
	
	
	public void deleteUser(UserDto req){
		log.info("deleteUser email {}", req.getEmail());
		
		String email = req.getEmail();
		String tmpCognitoEmail = Constant.PREFIX_TEMP_COGNITO_EMAIL + email;
		
		String tmpOwnerId = null;
		PersonalDto personalTmpRes = null;
		PersonalDto personalTmpReq = new PersonalDto();
		personalTmpReq.setEmail(tmpCognitoEmail);
		List<PersonalDto> personTmps = this.cognitoService.get(AWSCognitoService.GET_USER, personalTmpReq);
		if(!personTmps.isEmpty()) {
			personalTmpRes = personTmps.get(0);
			tmpOwnerId = personalTmpRes.getOwnerId();
			
			this.hotelInfoRepository.deleteByOwnerId(tmpOwnerId);
			this.staffInfoRepository.deleteByOwnerId(tmpOwnerId);
			this.tourCompanyRepository.deleteByOwnerId(tmpOwnerId);
			this.userInfoRepository.deleteByOwnerId(tmpOwnerId);
		}
		
		personalTmpReq.setEmail(email);
		personTmps = this.cognitoService.get(AWSCognitoService.GET_USER, personalTmpReq);
		if(!personTmps.isEmpty()) {
			personalTmpRes = personTmps.get(0);
			tmpOwnerId = personalTmpRes.getOwnerId();
			
			this.hotelInfoRepository.deleteByOwnerId(tmpOwnerId);
			this.staffInfoRepository.deleteByOwnerId(tmpOwnerId);
			this.tourCompanyRepository.deleteByOwnerId(tmpOwnerId);
			this.userInfoRepository.deleteByOwnerId(tmpOwnerId);
		}
		
		
		this.cognitoService.delete(Constant.PREFIX_TEMP_COGNITO_EMAIL + email);
		this.cognitoService.delete(email);

		log.info("end deleteUser email : {} successfully.", req.getEmail());
		
	}
	
	public void changePassword(UserPasswordDto req){
		log.info("Start changePassword.");
		
		if(ObjectUtils.isEmpty(req.getEmail())) {
			throw new ServiceException("Email is required.");
		}
		if(ObjectUtils.isEmpty(req.getOldPassword())) {
			throw new ServiceException("Old Password required.");
		}
		if(ObjectUtils.isEmpty(req.getNewPassword())) {
			throw new ServiceException("New Password required.");
		}
		if(req.getOldPassword().equals(req.getNewPassword())) {
			throw new ServiceException("Old Password and new password is same.");
		}

		this.cognitoService.changePassword(req.getEmail(), req.getNewPassword());

		log.info("end changePassword.");
		
	}
	
}

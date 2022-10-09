package com.r2dsolution.comein.service;

import java.time.LocalDate;
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
import org.springframework.util.ObjectUtils;

import com.r2dsolution.comein.cognito.AWSCognitoService;
import com.r2dsolution.comein.dao.BookingKycRepository;
import com.r2dsolution.comein.dao.BookingRepository;
import com.r2dsolution.comein.dao.HotelInfoRepository;
import com.r2dsolution.comein.dao.StaffInfoRepository;
import com.r2dsolution.comein.dao.UserInfoRepository;
import com.r2dsolution.comein.dto.BookingDto;
import com.r2dsolution.comein.dto.MailDto;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.entity.BookingInfo;
import com.r2dsolution.comein.entity.BookingKyc;
import com.r2dsolution.comein.entity.HotelInfo;
import com.r2dsolution.comein.entity.StaffInfo;
import com.r2dsolution.comein.entity.UserInfo;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.BookingInfoSpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.EmailValidation;


@Service
public class BookingService {

	private static Logger log = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private HotelInfoRepository hotelInfoRepository;
	
	@Autowired
	private StaffInfoRepository staffInfoRepository;
	
	@Autowired
	private BookingKycRepository bookingKycRepository;
	
	@Autowired
	private AWSCognitoService cognitoService;
	
	@Autowired
	private MailService mailService;
	
	public ResponseListDto<BookingDto> searchBookingInfo(String userToken, String referenceName, String bookingNo, Pageable pageable){
		log.info("searchBookingInfo referenceName : {}, bookingNo, userToken : {}", referenceName, bookingNo, userToken);
		ResponseListDto<BookingDto> response = new ResponseListDto<>();
		
		List<BookingDto> results = new ArrayList<>();
		
		UserInfo userInfo = userInfoRepository.findFirstByUserTokenAndRoleAndStatus(userToken, Constant.ROLE_HOTEL_STAFF, Constant.STATUS_ACTIVE);
		if(userInfo == null) {
			throw new ServiceException("Cannot found user infomation by user token.");
		}
		
			
		StaffInfo staffInfo = staffInfoRepository.findFirstByOwnerId(userInfo.getOwnerId());
		if(staffInfo == null) {
			throw new ServiceException("Cannot found user infomation by ownerId.");
		}
		
		Long hotelId = staffInfo.getHotelId();
		
		BookingInfo filter = new BookingInfo();
		filter.setHotelId(hotelId);
		filter.setRefName(referenceName);
		filter.setBookingNo(bookingNo);
		BookingInfoSpecification spec = new BookingInfoSpecification(filter);
		
		Sort sort = Sort.by(Direction.DESC, "updatedDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<BookingInfo> entities = bookingRepository.findAll(spec, pageable1);
		
		
		BookingDto dto = null;
		for(BookingInfo entity : entities) {
			dto = new BookingDto();
			dto.setId(entity.getId());
			dto.setBookingNo(entity.getBookingNo());
			dto.setRoomName(entity.getRoomName());
			dto.setRoomDesc(entity.getRoomDesc());
			dto.setOwnerId(entity.getOwnerId());
			dto.setReferenceName(entity.getRefName());
			dto.setCheckin(entity.getCheckin());
			dto.setCheckout(entity.getCheckout());
			dto.setVisitorAdult(entity.getVisitorAdult());
			dto.setVisitorChild(entity.getVisitorChild());
			dto.setStatus(entity.getStatus());
		    
			results.add(dto);
		}
		
		response.setDatas(results);
		
		Pageable page = entities.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), entities.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public BookingDto getBookingInfo(long id){
		log.info("getBookingInfo id : {}", id);;
		BookingDto response = null;
		
		Optional<BookingInfo> bookings = bookingRepository.findById(id);
		if(bookings.isPresent()) {
			
			BookingInfo entity = bookings.get();
		
			response = new BookingDto();
			response.setId(entity.getId());
			response.setBookingNo(entity.getBookingNo());
			response.setRoomName(entity.getRoomName());
			response.setRoomDesc(entity.getRoomDesc());
			response.setOwnerId(entity.getOwnerId());
			response.setReferenceName(entity.getRefName());
			response.setCheckin(entity.getCheckin());
			response.setCheckout(entity.getCheckout());
			response.setVisitorAdult(entity.getVisitorAdult());
			response.setVisitorChild(entity.getVisitorChild());
			response.setStatus(entity.getStatus());

		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	
	public List<BookingDto> saveBooking(BookingDto req, String userToken){
		log.info("Start saveBooking roomName : {}", req.getRoomName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getBookingNo())){
				throw new ServiceException("Booking No is require.");
			}
//			if(ObjectUtils.isEmpty(req.getEmail())){
//				throw new ServiceException("Email is require.");
//			}
			if(!ObjectUtils.isEmpty(req.getEmail()) && !EmailValidation.patternMatches(req.getEmail())){
				throw new ServiceException("Email is invalid.");
			}
//			if(ObjectUtils.isEmpty(req.getCustomerName())) {
//				throw new ServiceException("Customer Name is require.");	
//			}
//			if(ObjectUtils.isEmpty(req.getCustomerSurname())) {
//				throw new ServiceException("Customer Surname is require.");	
//			}
			if(ObjectUtils.isEmpty(req.getRoomName())) {
				throw new ServiceException("Room Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getCheckin())) {
				throw new ServiceException("Checkin is require.");	
			}
			if(ObjectUtils.isEmpty(req.getVisitorAdult())) {
				throw new ServiceException("Visitor Adult is require.");	
			}
		}
		
		List<BookingDto> results = new ArrayList<>();
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		try {
			UserInfo user = this.userInfoRepository.findFirstByUserTokenAndStatus(userToken, Constant.STATUS_ACTIVE);
			if(user == null) {
				throw new ServiceException("Cannot create Hotel Staff, User not found");
			}
			StaffInfo staff = this.staffInfoRepository.findFirstByOwnerId(user.getOwnerId());
			if(staff == null) {
				throw new ServiceException("Cannot create Hotel Staff, Staff not found.");
			}			
			Optional<HotelInfo> hotel = this.hotelInfoRepository.findById(staff.getHotelId());
			if(!hotel.isPresent()) {
				throw new ServiceException("Cannot create Hotel Staff, Hotel Info not found.");
			}
			Long hotelId = hotel.get().getId();
			
			//TODO comment insert customer
			/*
			Long customerId = null;
			CustomerInfo customer = this.customerInfoRepository.findByCustomerNameAndCustomerSurname(req.getCustomerName(), req.getCustomerSurname());
			if(customer != null) {
				customerId = customer.getId();
			} else {
				customer = new CustomerInfo();
				customer.setCustomerName(req.getCustomerName());
				customer.setCustomerSurname(req.getCustomerSurname());
				customer.setEmail(req.getEmail());
				customer.setMobileNo(req.getMobileNo());
				customer.setCreatedDate(currentTimestamp);
				customer.setCreatedBy(userToken);
				customer.setUpdatedDate(currentTimestamp);
				customer.setUpdatedBy(userToken);
				
				customer = this.customerInfoRepository.save(customer);
				customerId = customer.getId();
			}
			*/

			
			BookingInfo entity = new BookingInfo();
		
//			String ownerId = UUID.randomUUID().toString();
			
			log.info("saveBooking bookingNo : {}", req.getBookingNo());
			
//			entity.setBookingNo(new RandomString(6).nextString());
			entity.setBookingNo(req.getBookingNo());
			entity.setBookingDate(LocalDate.now());
			entity.setHotelId(hotelId);
			entity.setRoomName(req.getRoomName());
			entity.setRoomDesc(req.getRoomDesc());
//			entity.setOwnerId(ownerId);
			entity.setRefName(req.getReferenceName());
//			entity.setCustomerName(req.getCustomerName());
//			entity.setCustomerSurname(req.getCustomerSurname());
//			entity.setEmail(req.getEmail());
//			entity.setMobileNo(req.getMobileNo());
			entity.setCheckin(req.getCheckin());
			entity.setCheckout(req.getCheckout());
			entity.setVisitorAdult(req.getVisitorAdult());
			entity.setVisitorChild(req.getVisitorChild());
			entity.setStatus(Constant.STATUS_INVITED);
			entity.setCreatedDate(currentTimestamp);
			entity.setCreatedBy(userToken);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			bookingRepository.save(entity);
			
//			sendInvited(entity.getId(), req.getEmail(), userToken);
			
		}catch(Exception e) {
			log.error("Exception, ", e);
			throw new ServiceException(e);
		}
		
		log.info("End saveBooking roomName : {}", req.getRoomName());
		
		return results;
	}
	
	public void sendInvited(Long id, String email, String userToken) {
		
		if(!ObjectUtils.isEmpty(email) && !EmailValidation.patternMatches(email)){
			throw new ServiceException("Email is invalid.");
		}
		
		Optional<BookingInfo> bookings = bookingRepository.findById(id);
		if(bookings.isPresent()) {
			
			LocalDateTime currentTimestamp = LocalDateTime.now();
			
			BookingInfo entity = bookings.get();
			entity.setStatus(Constant.STATUS_INVITED);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			bookingRepository.save(entity);
			
			MailDto mailDto = new MailDto();
			mailDto.setTemplate("success_mail");
			mailDto.setTo(email);
			mailDto.setRole("customer");
			mailDto.setName("-");
			mailDto.setSurname("-");
			mailService.sendBookingSuccess(mailDto);
			
		} else {
			throw new ServiceException("Cannot invited, Data not found.");
		}
	}
	
	public List<PersonalDto> getBookingVisitor(BookingDto req, String userToken){
		log.info("getBookingVisitor id : {}, userToken : {}", req.getId(), userToken);
		List<PersonalDto> response = new ArrayList<>();
		
		List<BookingKyc> entities = this.bookingKycRepository.findByBookingIdOrderById(req.getId());
		
		PersonalDto dto = null;
		for(BookingKyc entity : entities) {
			
			if(!ObjectUtils.isEmpty(entity.getRefId())) {
				PersonalDto personalDto = null;
				PersonalDto personalTmpReq = new PersonalDto();
				personalTmpReq.setComeinId(entity.getRefId());
				List<PersonalDto> personTmps = this.cognitoService.get(AWSCognitoService.GET_USER, personalTmpReq);
				if(!personTmps.isEmpty()) {
					dto = new PersonalDto();
					
					personalDto = personTmps.get(0);
					String email = personalDto.getEmail();
					email = email.replaceFirst(Constant.PREFIX_TEMP_COGNITO_EMAIL, "");
	
					dto.setConsentId(entity.getId());		//booking_kyc.id
					dto.setIdNo(personalDto.getIdNo());
					dto.setEmail(email);
					dto.setFirstName(personalDto.getFirstName());
					dto.setLastName(personalDto.getLastName());
					dto.setBirthDate(personalDto.getBirthDate());
					dto.setIssueDate(personalDto.getIssueDate());
					dto.setExpireDate(personalDto.getExpireDate());
					
					dto.setReferenceName(personalDto.getReferenceName());
					dto.setConsent(entity.getConsent());
					if(Constant.FLAG_Y.equals(entity.getConsent())) dto.setStatus(Constant.STATUS_CONSENT_CONFIRM);
					else dto.setStatus(Constant.STATUS_CONSENT_INVALIDCONFIRM);
					
					
					response.add(dto);
				}
			}
		}
		
		
		return response;
	}
	
	public void confirmBookingConsnet(BookingDto req, String userToken){
		log.info("confirmBookingConsnet id : {}, consentId : {}, userToken : {}", req.getId(), req.getConsentId(), userToken);
		
		Optional<BookingKyc> opt = this.bookingKycRepository.findById(req.getConsentId());
		
		if(opt.isPresent()) {
			BookingKyc entity = opt.get();
			
			entity.setConsent(Constant.FLAG_Y);
			
			this.bookingKycRepository.save(entity);
		}
	}
	
	public void completeBooking(BookingDto req, String userToken){
		log.info("completeBookin id : {}, userToken : {}", req.getId(), userToken);
		
		Optional<BookingInfo> opt = this.bookingRepository.findById(req.getId());
		
		if(opt.isPresent()) {
			LocalDateTime currentTimestamp = LocalDateTime.now();
			
			BookingInfo entity = opt.get();
			
			entity.setStatus(Constant.STATUS_COMPLETED);
			entity.setUpdatedBy(userToken);
			entity.setUpdatedDate(currentTimestamp);
			
			this.bookingRepository.save(entity);
		}
	}
}

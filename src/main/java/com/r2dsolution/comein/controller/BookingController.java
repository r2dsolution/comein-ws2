package com.r2dsolution.comein.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.r2dsolution.comein.dto.BookingDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.service.BookingService;

@Controller
public class BookingController {
	
	private static final Logger log = LoggerFactory.getLogger(BookingController.class);

	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/bookings")
	public ResponseEntity<ResponseListDto<BookingDto>> searchBookingInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken,
			@RequestParam(required = false) String referenceName, Pageable pageable) {
		log.info("Start searchBookingInfo...referenceName : {}", referenceName);
		
		ResponseListDto<BookingDto> res = this.bookingService.searchBookingInfo(userToken, referenceName, pageable);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<BookingDto> getBookingInfo(@PathVariable Long id) {
		log.info("Start getBookingInfo.....id : {}", id);
		
		BookingDto res = this.bookingService.getBookingInfo(id);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/bookings")
	public ResponseEntity<Void> saveBooking(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody BookingDto req) {
		log.info("Start saveBooking.....roomName : {}", req.getRoomName());
		
		this.bookingService.saveBooking(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
	
	@PostMapping("/bookings/{id}/invited")
	public ResponseEntity<Void> invitedBookingInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @RequestBody BookingDto req) {
		log.info("Start invitedBookingInfo.....id : {}", id);
			
		this.bookingService.sendInvited(id, req.getEmail(), userToken);
			
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/bookings/visitors")
	public ResponseEntity<List<PersonalDto>> getBookingVisitor(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody BookingDto req) {
		log.info("Start getBookingVisitor.....id : {}", req.getId());
			
		List<PersonalDto> res = this.bookingService.getBookingVisitor(req, userToken);
			
		return ResponseEntity.ok(res);
	}
	
	@PutMapping("/bookings/visitors/confirm")
	public ResponseEntity<Void> confirmBookingConsent(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody BookingDto req) {
		log.info("Start getBookingVisitor.....id : {}, consentId : {}", req.getId(), req.getConsentId());
			
		this.bookingService.confirmBookingConsnet(req, userToken);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/bookings/complete")
	public ResponseEntity<Void> completeBooking(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody BookingDto req) {
		log.info("Start getBookingVisitor.....id : {}", req.getId());
			
		this.bookingService.completeBooking(req, userToken);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

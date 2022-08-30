package com.r2dsolution.comein.controller;



import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.TourBookingDto;
import com.r2dsolution.comein.service.TourBookingService;


@RestController
public class TourBookingController {

	private static Logger log = LoggerFactory.getLogger(TourBookingController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourBookingService tourBookingService;
	
	@GetMapping("/tour-bookings")
	public ResponseEntity<ResponseListDto<TourBookingDto>> searchTourBooking(
			@RequestParam(required = false) String dateType, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, 
			@RequestParam(required = false) String bookingCode, @RequestParam(required = false) String referenceName, Pageable pageable) {
		log.info("searchTourBooking.....startDate : {}, endDate : {}", startDate, endDate);
		
		ResponseListDto<TourBookingDto> res = this.tourBookingService.searchTourBooking(dateType, startDate, endDate, bookingCode, referenceName, pageable);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/tour-bookings/{bookingCode}")
	public ResponseEntity<TourBookingDto> getTourBookingInfo(@PathVariable String bookingCode) {
		log.info("getTourBookingInfo.....bookingCode : {}", bookingCode);
		
		TourBookingDto res = this.tourBookingService.getTourBooking(bookingCode);
		
        return ResponseEntity.ok(res);
	}
	
        
	@PutMapping("/tour-bookings/cancel")
	public ResponseEntity<Void> cancelTourBooking(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TourBookingDto req) {
		log.info("cancelTourBooking.....bookingCode : {}", req.getBookingCode());
			
		this.tourBookingService.cancelTourBooking(req.getBookingCode(), userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@PutMapping("/tour-bookings/changedate")
	public ResponseEntity<Void> changeTourBooking(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TourBookingDto req) {
		log.info("changeTourBooking.....bookingCode : {}", req.getBookingCode());
			
		this.tourBookingService.changeTourBooking(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
}

package com.r2dsolution.comein.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.r2dsolution.comein.dto.OtaBookingDto;
import com.r2dsolution.comein.dto.PayableBookingDetailDto;
import com.r2dsolution.comein.dto.PayableBookingDto;
import com.r2dsolution.comein.service.PayableBookingService;

@Controller
public class PayableBookingController {

	private static Logger log = LoggerFactory.getLogger(PayableBookingController.class);
	
	@Autowired
	private PayableBookingService payableBookingService;
	
	
	@GetMapping("/payable-bookings")
	public ResponseEntity<List<PayableBookingDto>> listPayableBookings(@RequestParam(required = false) Long companyId) {
		log.info("Start.....listPayableBookings");
		
		List<PayableBookingDto> res = this.payableBookingService.getPayableTourBooking(companyId);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/payable-bookings/{bookingCode}")
	public ResponseEntity<PayableBookingDetailDto> getPayableBookingByCode(@PathVariable String bookingCode) {
		log.info("Start getPayableBookingByCode.....code : {}", bookingCode);
		
		PayableBookingDetailDto res = this.payableBookingService.getPayableTourBookingByBookingCode(bookingCode);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/payable-bookings/{bookingCode}")
	public ResponseEntity<Void> savePayableBookingByCode(@PathVariable String bookingCode, @RequestBody PayableBookingDetailDto req) {
		log.info("Start savePayableBookingByCode.....code : {}", bookingCode);
		
		this.payableBookingService.savePayableTourBookingByBookingCode(bookingCode, req);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}

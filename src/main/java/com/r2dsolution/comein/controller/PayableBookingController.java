package com.r2dsolution.comein.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.r2dsolution.comein.dto.PayableBookingDetailDto;
import com.r2dsolution.comein.dto.PayableBookingDto;
import com.r2dsolution.comein.service.PayableBookingService;

@Controller
public class PayableBookingController {

	private static Logger log = LoggerFactory.getLogger(PayableBookingController.class);
	
	@Autowired
	private PayableBookingService payableBookingService;
	
	
	@GetMapping("/payable-bookings")
	public ResponseEntity<List<PayableBookingDto>> listPayableBookings() {
		log.info("Start.....listPayableBookings");
		
		List<PayableBookingDto> res = this.payableBookingService.getPayableTourBooking();
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/payable-bookings/{bookingCode}")
	public ResponseEntity<PayableBookingDetailDto> getPayableBookingByCode(@PathVariable String bookingCode) {
		log.info("Start getPayableBookingByCode.....code : {}", bookingCode);
		
		PayableBookingDetailDto res = this.payableBookingService.getPayableTourBookingByBookingCode(bookingCode);
		
        return ResponseEntity.ok(res);
	}
	
	
}

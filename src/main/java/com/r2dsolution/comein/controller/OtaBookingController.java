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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.r2dsolution.comein.client.SimpleQueueServiceClient;
import com.r2dsolution.comein.dto.OtaBookingDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.model.EmailRequest;
import com.r2dsolution.comein.service.OtaBookingService;


@RestController
public class OtaBookingController {

	private static Logger log = LoggerFactory.getLogger(OtaBookingController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private OtaBookingService otaBookingService;
	
	@GetMapping("/ota-bookings")
	public ResponseEntity<ResponseListDto<OtaBookingDto>> searchOtaBooking(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, 
			Pageable pageable) {
		log.info("searchOtaBooking.....startDate : {}, endDate : {}", startDate, endDate);
		
		ResponseListDto<OtaBookingDto> res = this.otaBookingService.searchOtaBooking(startDate, endDate, pageable);
		
        return ResponseEntity.ok(res);
	}
        
	@GetMapping("/ota-bookings/{id}")
	public ResponseEntity<OtaBookingDto> getOtaBooking(
			@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id) {
		log.info("getOtaBooking.....id : {}", id);
		
		OtaBookingDto res = this.otaBookingService.getOtaBooking(id);
		
        return ResponseEntity.ok(res);
	}
	@PostMapping("/ota-bookings/manual")
	public ResponseEntity<Void> otaBookingManual(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody OtaBookingDto req) {
		log.info("otaBookingManual.....id : {}", req.getId());
			
		this.otaBookingService.manualMatch(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/ota-bookings")
	public ResponseEntity<Void> saveOtaBooking(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody OtaBookingDto req) {
		log.info("saveOtaBooking.....hotelName : {}", req.getHotelName());
			
		this.otaBookingService.saveOtaBooking(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@Autowired
	private SimpleQueueServiceClient client;
	
	@GetMapping("/ota-bookings/email")
	public ResponseEntity<Void> testSendEmailWithSqs() {
		log.info("testSendEmailWithSqs.....");

		
		EmailRequest req = tokenToEmailRequest("jaroon.k@gmail.com");
		//client.sendMessage(req);
		AmazonSQS sqsClient = client.initClient();
		String url = client.urlSendEmail(sqsClient);
		client.send(sqsClient, url, req);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private EmailRequest tokenToEmailRequest(String email) {
		EmailRequest req = new EmailRequest();
//		req.setTemplate("PDPAInvite");
//		req.setEmail(email);
//		req.getParams().put("ref_name", "mock_refname");	
//		req.getParams().put("token", "mock_token");
//		req.getParams().put("url", "mock_url");	
		req.setTemplate("invite_mail");
		req.setEmail(email);
		req.getParams().put("role", "mock_refname");	
		req.getParams().put("link", "mock_url");	
		return req;
	}
	
}

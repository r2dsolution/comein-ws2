package com.r2dsolution.comein.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.r2dsolution.comein.dto.PersonalConsentDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.service.PersonalService;

@Controller
public class PersonalController {
	private static Logger log = LoggerFactory.getLogger(PersonalController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private PersonalService personalService;
	
	
	@PostMapping("/personals/info")
	public ResponseEntity<PersonalDto> getPersonalInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody PersonalDto req) {
		log.info("getPersonalInfo.....ownerId : {}", req.getOwnerId());
		
		PersonalDto res = this.personalService.getPersonalInfo(req.getOwnerId());
		
        return ResponseEntity.ok(res);
	}
	
	@PutMapping("/personals/info")
	public ResponseEntity<Void> updatePersonalInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody PersonalDto req) {
		log.info("updatePersonalInfo.....ownerId : {}", req.getOwnerId());
		
		this.personalService.updatePersonalInfo(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/personals/consents")
	public ResponseEntity<PersonalDto> getPersonalConsent(@RequestBody PersonalConsentDto req) {
		log.info("getPersonalConsent.....token : {}", req.getToken());
		
		PersonalDto res = this.personalService.getPersonalConsent(req);
		
        return ResponseEntity.ok(res);
	}
	
}

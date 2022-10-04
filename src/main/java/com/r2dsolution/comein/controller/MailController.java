package com.r2dsolution.comein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.r2dsolution.comein.dto.MailDto;
import com.r2dsolution.comein.service.MailService;

@Controller
public class MailController {

	@Autowired
	private MailService mailService;
	
	@PostMapping("/mails/send")
	public ResponseEntity<Void> sendEmail(@RequestBody MailDto req) {
		System.out.println("sendEmail.....");
		
		if(req.getTemplate() != null) {
			if("invite_mail".equals(req.getTemplate())) {
				mailService.sendRegisterInvite(req);
//			} else if("verify_mail".equals(req.getTemplate())) {
//				mailService.sendRegisterHotelAdminVerify(req);
			} else if("success_mail".equals(req.getTemplate())) {
				mailService.sendRegisterHotelAdminSuccess(req);
			} else if("booking_mail".equals(req.getTemplate())) {
				mailService.sendBookingSuccess(req);
			}
		}
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
}

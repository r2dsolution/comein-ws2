package com.r2dsolution.comein.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.r2dsolution.comein.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.TourCompanyDto;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.service.TourCompanyService;
import com.r2dsolution.comein.util.Constant;

@Controller
public class TourCompanyController {

	private static Logger log = LoggerFactory.getLogger(TourCompanyController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourCompanyService tourCompanyService;
	
	@GetMapping("/tour-companies")
	public ResponseEntity<ResponseListDto<TourCompanyDto>> searchTourCompany(@RequestParam(required = false) String email, 
			@RequestParam(required = false) String companyName,  Pageable pageable) {
		log.info("searchTourCompany.....email : {}, companyName : {}", email, companyName);
		
		ResponseListDto<TourCompanyDto> res = this.tourCompanyService.searchTourCompany(email, companyName, pageable);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/tour-companies/{id}")
	public ResponseEntity<TourCompanyDto> getTourCompanyInfo(@PathVariable Long id) {
		log.info("getTourCompanyInfo.....id : {}", id);
		
		TourCompanyDto res = this.tourCompanyService.getTourCompany(id);
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/tour-companies")
	public ResponseEntity<Void> saveTourCompany(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TourCompanyDto req) {
		log.info("saveTourCompany.....companyName : {}", req.getCompanyName());
			
		this.tourCompanyService.saveTourCompany(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
	
	@PostMapping("/tour-companies/{id}/{status}")
	public ResponseEntity<TourCompanyDto> patchTourCompany(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @PathVariable String status) {
		log.info("patchTourCompany.....id : {}, status : {}", id, status);
			
		TourCompanyDto res = null;
		if(!ObjectUtils.isEmpty(status)) {
			if(Constant.STATUS_VERIFY.equalsIgnoreCase(status)) {
				this.tourCompanyService.verifyTourCompany(id, userToken);
				res = this.tourCompanyService.getTourCompany(id);
			} else if(Constant.STATUS_UNVERIFY.equalsIgnoreCase(status)) {
				this.tourCompanyService.unverifyTourCompany(id, userToken);
				res = this.tourCompanyService.getTourCompany(id);
			} else {
				throw new ServiceException("Action not found.");
			}
		} else {
			throw new ServiceException("Action not found.");
		}
		return ResponseEntity.ok(res);	
	}
	
	@PutMapping("/tour-companies/{id}")
	public ResponseEntity<TourCompanyDto> updateTourCompany(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @RequestBody TourCompanyDto req) {
		log.info("updateTourCompany.....id : {}, companyName : {}", id, req.getCompanyName());
			
		this.tourCompanyService.updateTourCompany(id, req, userToken);
		TourCompanyDto res = this.tourCompanyService.getTourCompany(id);
				
		return ResponseEntity.ok(res);	
	}
	
	@PutMapping("/tour-companies/profiles")
	public ResponseEntity<Void> updateTourCompanyProfile(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TourCompanyDto req) {
		log.info("updateTourCompanyProfile.....");
			
		this.tourCompanyService.updateTourCompanyProfile(req, userToken);
				
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

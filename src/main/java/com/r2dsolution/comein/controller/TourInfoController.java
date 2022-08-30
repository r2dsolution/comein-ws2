package com.r2dsolution.comein.controller;



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

import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.TourInfoDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class TourInfoController {

	private static Logger log = LoggerFactory.getLogger(TourInfoController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	@GetMapping("/tours")
	public ResponseEntity<ResponseListDto<TourInfoDto>> searchTourInfo(@RequestParam(required = false) String email, 
			@RequestParam(required = false) String tourName,  Pageable pageable) {
		log.info("searchTourInfo.....email : {}, tourName : {}", email, tourName);
		
		ResponseListDto<TourInfoDto> res = this.tourInfoService.searchTourInfo(email, tourName, pageable);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/tours/{id}")
	public ResponseEntity<TourInfoDto> getTourInfoInfo(@PathVariable Long id) {
		log.info("getTourInfoInfo.....id : {}", id);
		
		TourInfoDto res = this.tourInfoService.getTourInfo(id);
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/tours")
	public ResponseEntity<Void> saveTourInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TourInfoDto req) {
		log.info("saveTourInfo.....tourName : {}", req.getCompanyName());
			
		this.tourInfoService.saveTourInfo(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	

	
	@PutMapping("/tours/{id}")
	public ResponseEntity<TourInfoDto> updateTourInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @RequestBody TourInfoDto req) {
		log.info("updateTourInfo.....id : {}, tourName : {}", id, req.getCompanyName());
			
		this.tourInfoService.updateTourInfo(id, req, userToken);
		TourInfoDto res = this.tourInfoService.getTourInfo(id);
				
		return ResponseEntity.ok(res);	
	}
	
}

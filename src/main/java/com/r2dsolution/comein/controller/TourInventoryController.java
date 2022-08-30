package com.r2dsolution.comein.controller;



import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.r2dsolution.comein.dto.TourInventoryDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class TourInventoryController {

	private static Logger log = LoggerFactory.getLogger(TourInventoryController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	@GetMapping("/tour-inventorys/{id}")
	public ResponseEntity<List<TourInventoryDto>> getTourInventoryInfo(@PathVariable Long id,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate 
	) {
		log.info("listTourInventoryInfo.....id : {}", id);
		
		List<TourInventoryDto> res = this.tourInfoService.getTourInventoryInfo(id, startDate, endDate);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/tour-inventorys/{id}")
	public ResponseEntity<Void> saveTourInventoryInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @RequestBody TourInventoryDto req) {
		log.info("saveTourInventoryInfo.....id : {}, dateFrom : {}, dateTo : {}", id, req.getStartDate(), req.getEndDate());
			
		this.tourInfoService.saveTourInventory(userToken, id, req);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/tour-inventorys/{id}")
	public ResponseEntity<Void> updateTourInventoryInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @RequestBody TourInventoryDto req) {
		log.info("updateTourInventoryInfo.....id : {}, tourDate : {}", id, req.getTourDate());
			
		this.tourInfoService.updateTourInventory(userToken, id, req);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
}

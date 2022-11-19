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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.StaffDto;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.service.StaffInfoService;
import com.r2dsolution.comein.util.Constant;

@Controller
public class HotelStaffController {
	
	private static Logger log = LoggerFactory.getLogger(HotelStaffController.class);

	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private StaffInfoService staffInfoService;
	
	@GetMapping("/hotel-staffs")
	public ResponseEntity<ResponseListDto<StaffDto>> searchHotelStaffInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, 
			@RequestParam(required = false) String email, 
			@RequestParam(required = false) String staffName,  Pageable pageable) {

		log.info("searchHotelStaffInfo.....email : {}, staffName : {}", email, staffName);
		
		ResponseListDto<StaffDto> res = this.staffInfoService.searchStaffInfo(userToken, email, staffName, pageable);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/hotel-staffs/{id}")
	public ResponseEntity<StaffDto> getHotelStaffInfo(@PathVariable Long id) {
		log.info("getHotelStaffInfo.....id : {}", id);
		
		StaffDto res = this.staffInfoService.getHotelStaffInfo(id);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/hotel-staffs")
	public ResponseEntity<Void> saveHotelStaff(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody StaffDto req) {
		log.info("saveHotelStaff.....referenceName : {}", req.getReferenceName());
		
		this.staffInfoService.saveHotelStaff(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
	
	@PostMapping("/hotel-staffs/{id}/{action}")
	public ResponseEntity<StaffDto> patchHotelStaffInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @PathVariable String action) {
		log.info("patchHotelStaffInfo.....id : {}, action : {}", id, action);
		
		StaffDto res = null;
		if(!ObjectUtils.isEmpty(action)) {
			if(Constant.STATUS_VERIFY.equalsIgnoreCase(action)) {
				this.staffInfoService.verifyHotelStaff(id, userToken);
				res = this.staffInfoService.getHotelStaffInfo(id);
			} else if(Constant.STATUS_UNVERIFY.equalsIgnoreCase(action)) {
				this.staffInfoService.unverifyHotelStaff(id, userToken);
				res = this.staffInfoService.getHotelStaffInfo(id);
			} else {
				throw new ServiceException("Action not found.");
			}
		} else {
			throw new ServiceException("Action not found.");
		}
		return ResponseEntity.ok(res);	
	}
}

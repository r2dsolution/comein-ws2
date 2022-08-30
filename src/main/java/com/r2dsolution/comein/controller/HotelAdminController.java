package com.r2dsolution.comein.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.r2dsolution.comein.dto.HotelAdminDto;
import com.r2dsolution.comein.dto.HotelNameDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.service.HotelInfoService;
import com.r2dsolution.comein.util.Constant;

@Controller
public class HotelAdminController {

	private static Logger log = LoggerFactory.getLogger(HotelAdminController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private HotelInfoService hotelInfoService;
	
	@GetMapping("/hotel-admins")
	public ResponseEntity<ResponseListDto<HotelAdminDto>> searchHotelInfo(@RequestParam(required = false) String email, 
			@RequestParam(required = false) String hotelName,  Pageable pageable) {
		log.info("searchHotelInfo.....email : {}, hotelName : {}", email, hotelName);
		
		ResponseListDto<HotelAdminDto> res = this.hotelInfoService.searchHotelInfo(email, hotelName, pageable);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/hotel-admins/{id}")
	public ResponseEntity<HotelAdminDto> getHotelAdminInfo(@PathVariable Long id) {
		log.info("getHotelAdminInfo.....id : {}", id);
		
		HotelAdminDto res = this.hotelInfoService.getHotelInfo(id);
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/hotel-admins")
	public ResponseEntity<Void> saveHotelAdmin(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody HotelAdminDto req) {
		log.info("saveHotelAdmin.....hotelName : {}", req.getHotelName());
			
		this.hotelInfoService.saveHotelAdmin(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
	
	@PostMapping("/hotel-admins/{id}/{status}")
	public ResponseEntity<HotelAdminDto> patchHotelInfo(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @PathVariable String status) {
		log.info("patchHotelInfo.....id : {}, status : {}", id, status);
			
		HotelAdminDto res = null;
		if(!ObjectUtils.isEmpty(status)) {
			if(Constant.STATUS_VERIFY.equalsIgnoreCase(status)) {
				this.hotelInfoService.verifyHotelAdmin(id, userToken);
				res = this.hotelInfoService.getHotelInfo(id);
			} else if(Constant.STATUS_UNVERIFY.equalsIgnoreCase(status)) {
				this.hotelInfoService.unverifyHotelAdmin(id, userToken);
				res = this.hotelInfoService.getHotelInfo(id);
			} else {
				throw new ServiceException("Action not found.");
			}
		} else {
			throw new ServiceException("Action not found.");
		}
		return ResponseEntity.ok(res);	
	}
	
	@GetMapping("/hotels/names")
	public ResponseEntity<List<HotelNameDto>> listAllHotelName() {
		log.info("HotelNameDto.....");
		
		List<HotelNameDto> res = this.hotelInfoService.listAllHotelName();
		
        return ResponseEntity.ok(res);
	}
}

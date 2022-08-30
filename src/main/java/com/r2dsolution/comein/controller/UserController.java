package com.r2dsolution.comein.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.r2dsolution.comein.dto.UserDto;
import com.r2dsolution.comein.dto.UserPasswordDto;
import com.r2dsolution.comein.service.UserInfoService;

@Controller
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/users/ref")
	public ResponseEntity<UserDto> getUserInfoByRefNo(@RequestBody UserDto req) {
		log.info("getUserInfoByRefNo.....refNo : {}", req.getRefNo());
		
		UserDto res = this.userInfoService.getUserInfoByRefNo(req.getRefNo());
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/users/info")
	public ResponseEntity<UserDto> getUserInfoByToken(@RequestBody UserDto req) {
		log.info("getUserInfoByRefNo.....refNo : {}", req.getRefNo());
		
		UserDto res = this.userInfoService.getUserInfoByToken(req.getUserToken());
		
        return ResponseEntity.ok(res);
	}
	
	
	@PostMapping("/users/roles")
	public ResponseEntity<List<String>> getUserRole(@RequestBody UserDto req) {
		log.info("getUserRole.....userToken : {}", req.getUserToken());
		
		List<String> res = this.userInfoService.getUserRole(req.getUserToken());
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/users/permissions")
	public ResponseEntity<List<String>> getUserRolePermission(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody UserDto req) {
		log.info("getUserRolePermission.....role : {}", req.getRole());
		
		List<String> res = this.userInfoService.getUserRolePermission(req.getUserToken(), req.getRole());
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/users/permissions/{role}")
	public ResponseEntity<List<String>> getUserRolePermission(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable String role) {
		log.info("getUserRolePermission.....role : {}", role);
		
		List<String> res = this.userInfoService.getUserRolePermission(userToken, role);
		
        return ResponseEntity.ok(res);
	}
	
	
	@PostMapping("/users/signup")
	public ResponseEntity<Void> enableUser(@RequestBody UserDto req) {
		log.info("enableUser.....refNo : {}", req.getRefNo());
			
		this.userInfoService.enableUser(req);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<Void> deleteUser(@RequestBody UserDto req) {
		log.info("deleteUser.....email : {}", req.getEmail());
			
		this.userInfoService.deleteUser(req);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/users/change-password")
	public ResponseEntity<Void> changePassword(@RequestBody UserPasswordDto req) {
		log.info("changePassword....");
			
		this.userInfoService.changePassword(req);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}

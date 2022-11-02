package com.r2dsolution.comein.controller;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.r2dsolution.comein.dto.PayableCompanyDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class PayableCompanyController {

	private static Logger log = LoggerFactory.getLogger(PayableCompanyController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	@GetMapping("/payable-companies/{companyId}")
	public ResponseEntity<List<PayableCompanyDto>> getPayableCompanyById(@PathVariable Long companyId) {
		log.info("Start getPayableCompanyById.....code : {}", companyId);
		
//		TourInfoDto res = this.tourInfoService.getTourInfo(id);
		//TODO mock Test
		List<PayableCompanyDto> res = new ArrayList<>();
		
		PayableCompanyDto dto = new PayableCompanyDto();
		dto.setPeriodId(1L);
		dto.setDateFrom(LocalDate.of(2022, 1, 1));
		dto.setDateTo(LocalDate.of(2022, 1, 15));
		dto.setBookingCode("B94239");
		dto.setTourName("spider man");
		dto.setTourDate(LocalDate.of(2022, 1, 1));
		dto.setStatus("Open");
		dto.setNetValue(BigDecimal.valueOf(2000));
		dto.setTotal(BigDecimal.valueOf(43450));
		res.add(dto);
		
		dto = new PayableCompanyDto();
		dto.setPeriodId(1L);
		dto.setDateFrom(LocalDate.of(2022, 1, 1));
		dto.setDateTo(LocalDate.of(2022, 1, 15));
		dto.setBookingCode("B30928");
		dto.setTourName("Avenger Team");
		dto.setTourDate(LocalDate.of(2022, 1, 1));
		dto.setStatus("Open");
		dto.setNetValue(BigDecimal.valueOf(10000));
		dto.setTotal(BigDecimal.valueOf(43450));
		res.add(dto);
		
		dto = new PayableCompanyDto();
		dto.setPeriodId(1L);
		dto.setDateFrom(LocalDate.of(2022, 1, 1));
		dto.setDateTo(LocalDate.of(2022, 1, 15));
		dto.setBookingCode("B38372");
		dto.setTourName("Iron man");
		dto.setTourDate(LocalDate.of(2022, 1, 3));
		dto.setStatus("Open");
		dto.setNetValue(BigDecimal.valueOf(1500));
		dto.setTotal(BigDecimal.valueOf(43450));
		res.add(dto);
		
		dto = new PayableCompanyDto();
		dto.setPeriodId(1L);
		dto.setDateFrom(LocalDate.of(2022, 1, 1));
		dto.setDateTo(LocalDate.of(2022, 1, 15));
		dto.setBookingCode("B39870");
		dto.setTourName("Ant man");
		dto.setTourDate(LocalDate.of(2022, 1, 8));
		dto.setStatus("Open");
		dto.setNetValue(BigDecimal.valueOf(9500));
		dto.setTotal(BigDecimal.valueOf(43450));
		res.add(dto);
		
		
        return ResponseEntity.ok(res);
	}
	
	
}

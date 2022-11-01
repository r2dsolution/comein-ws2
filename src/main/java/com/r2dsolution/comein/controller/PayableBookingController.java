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

import com.r2dsolution.comein.dto.HotelPayableNoteDto;
import com.r2dsolution.comein.dto.PayableBookingDetailDto;
import com.r2dsolution.comein.dto.PayableBookingDto;
import com.r2dsolution.comein.dto.ReceivableNoteDto;
import com.r2dsolution.comein.dto.TourPayableNoteDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class PayableBookingController {

	private static Logger log = LoggerFactory.getLogger(PayableBookingController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	
	@GetMapping("/payable-bookings")
	public ResponseEntity<List<PayableBookingDto>> listPayableBookings() {
		log.info("Start.....listPayableBookings");
		
//		List<PaymentDto> res = this.provinceService.getAllProvince(country);
		
		//TODO mock Test
		List<PayableBookingDto> res = new ArrayList<>();
		PayableBookingDto dto = new PayableBookingDto();
	    dto.setBookingCode("B94239");
	    dto.setTourDate(LocalDate.of(2022, 1, 1));
	    dto.setStatus("Confirmed");
	    dto.setPaymentMethod("Credit");
	    dto.setPaymentDate(LocalDate.of(2021, 12, 30));
	    dto.setSellValue(BigDecimal.valueOf(2230));
		res.add(dto);
		
		dto = new PayableBookingDto();
	    dto.setBookingCode("B30928");
	    dto.setTourDate(LocalDate.of(2022, 1, 1));
	    dto.setStatus("Confirmed");
	    dto.setPaymentMethod("Cash");
	    dto.setPaymentDate(LocalDate.of(2021, 12, 28));
	    dto.setSellValue(BigDecimal.valueOf(12000));
		res.add(dto);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/payable-bookings/{bookingCode}")
	public ResponseEntity<PayableBookingDetailDto> getPayableBookingByCode(@PathVariable String bookingCode) {
		log.info("Start getPayableBookingByCode.....code : {}", bookingCode);
		
//		TourInfoDto res = this.tourInfoService.getTourInfo(id);
		//TODO mock Test
		PayableBookingDetailDto res = new PayableBookingDetailDto();
		
		ReceivableNoteDto recv = new ReceivableNoteDto();
		recv.setTransactionDate(LocalDate.of(2021, 12, 28));
		recv.setPaymentMethod("Cash");
		recv.setReceive(BigDecimal.valueOf(12000));
		recv.setTotal(BigDecimal.valueOf(12000));
		res.setReceivableNote(recv);
		
		TourPayableNoteDto tour = new TourPayableNoteDto();
		tour.setTransactionDate(LocalDate.of(2022, 1, 14));
		tour.setReceive(BigDecimal.valueOf(12000));
		tour.setComeinRate(BigDecimal.valueOf(-1200));
		tour.setHotelRate(BigDecimal.valueOf(-800));
		tour.setTotal(BigDecimal.valueOf(10000));
		res.setTourPayableNote(tour);
		
		HotelPayableNoteDto hotel = new HotelPayableNoteDto();
		hotel.setTransactionDate(LocalDate.of(2022, 1, 14));
		hotel.setHotelRate(BigDecimal.valueOf(800));
		hotel.setTotal(BigDecimal.valueOf(800));
		res.setHotelPayableNote(hotel);
		
        return ResponseEntity.ok(res);
	}
	
	
}

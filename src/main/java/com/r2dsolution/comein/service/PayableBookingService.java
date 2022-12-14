package com.r2dsolution.comein.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.AccountInfoRepository;
import com.r2dsolution.comein.dao.AccountingTransactionRepository;
import com.r2dsolution.comein.dao.PayTransactionRepository;
import com.r2dsolution.comein.dao.PayableBookingViewRepository;
import com.r2dsolution.comein.dao.PayablePeriodRepository;
import com.r2dsolution.comein.dao.ReceiveTransactionRepository;
import com.r2dsolution.comein.dao.TopUpRateCompanyRepository;
import com.r2dsolution.comein.dao.TopUpRateDefaultRepository;
import com.r2dsolution.comein.dao.TourBookingRepository;
import com.r2dsolution.comein.dto.HotelPayableNoteDto;
import com.r2dsolution.comein.dto.PayableBookingDetailDto;
import com.r2dsolution.comein.dto.PayableBookingDto;
import com.r2dsolution.comein.dto.PayablePeriodDto;
import com.r2dsolution.comein.dto.ReceivableNoteDto;
import com.r2dsolution.comein.dto.TourPayableNoteDto;
import com.r2dsolution.comein.entity.AccountingTransaction;
import com.r2dsolution.comein.entity.PayTransaction;
import com.r2dsolution.comein.entity.PayableBookingView;
import com.r2dsolution.comein.entity.PayablePeriod;
import com.r2dsolution.comein.entity.ReceiveTransaction;
import com.r2dsolution.comein.entity.TourBooking;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.DateUtils;
import com.r2dsolution.comein.util.NumberUtils;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class PayableBookingService {

	private static Logger log = LoggerFactory.getLogger(PayableBookingService.class);
	
	@Autowired
	private PayableBookingViewRepository payableBookingViewRepository;

	@Autowired
	private TopUpRateDefaultRepository topUpRateDefaultRepository;
	
	@Autowired
	private TopUpRateCompanyRepository topUpRateCompanyRepository;
	
	@Autowired
	private AccountingTransactionRepository accountingTransactionRepository;
	
	@Autowired
	private ReceiveTransactionRepository receiveTransactionRepository;

	@Autowired
	private PayTransactionRepository payTransactionRepository;

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Autowired
	private PayablePeriodRepository payablePeriodRepository;

	@Autowired
	private TourBookingRepository tourBookingRepository;

	public List<PayableBookingDto> getPayableTourBooking(){
		log.info("getPayableTourBooking ...");
		List<PayableBookingDto> response = new ArrayList<>();;
		
		List<PayableBookingView> entities = payableBookingViewRepository.findAll();
		if(!entities.isEmpty()) {
			PayableBookingDto dto = null;
			for(PayableBookingView entity : entities) {
				dto = new PayableBookingDto();
				dto.setCompanyId(entity.getCompanyId());
			    dto.setBookingCode(entity.getBookingCode());
			    dto.setTourDate(entity.getTourDate());
			    dto.setStatus(entity.getStatus());
			    dto.setPaymentMethod(entity.getPaymentMethod());
			    dto.setPaymentDate(entity.getPaymentDate());
			    dto.setSellValue(entity.getTotalSellValue());
			    
			    response.add(dto);
			}
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public PayableBookingDetailDto getPayableTourBookingByBookingCode(String bookingCode){
		log.info("getPayableTourBookingByBookingCode bookingCode : {}", bookingCode);
		PayableBookingDetailDto response = new PayableBookingDetailDto();
		
		PayableBookingView entity = payableBookingViewRepository.findFirstByBookingCode(bookingCode);
		if(entity != null) {
			
			LocalDate currentDate = LocalDate.now();
			String hotels = entity.getHotels();
			BigDecimal receive = NumberUtils.toBigDecimal(entity.getTotalSellValue());
			BigDecimal comeinRate = NumberUtils.toBigDecimal(entity.getComeinSellValue());
			BigDecimal hotelRate = NumberUtils.toBigDecimal(entity.getHotelSellValue());
			if(StringUtils.isEmpty(hotels)) {
				comeinRate = comeinRate.add(hotelRate);
				hotelRate = BigDecimal.ZERO;
			}
			BigDecimal totalRateTour = receive.subtract(comeinRate).subtract(hotelRate);
			
			ReceivableNoteDto recv = new ReceivableNoteDto();
			recv.setTransactionDate(currentDate);
			recv.setPaymentMethod(entity.getPaymentMethod());
			recv.setReceive(receive);
			recv.setTotal(receive);
			response.setReceivableNote(recv);
			
			
//			Long companyId = entity.getCompanyId();
//			List<TopupRateCompany> compRates = topUpRateCompanyRepository.findByCompanyId(companyId);
//			if(compRates.isEmpty())
//				throw new ServiceException("Data not found.");
//			
//			TopupRateCompany firstCompRate = compRates.get(0);
//			boolean useDefault = firstCompRate.getUseDefault();
//			if(useDefault) {
//				List<TopupRateDefault> defaultRates = topUpRateDefaultRepository.findAll();
//				for(TopupRateDefault rate : defaultRates) {
//					if(receive.compareTo(rate.getMinPeriod()) < 0 && receive.compareTo(rate.getMaxPeriod()) > 0) {
//						comeinRate = rate.getComeinRate();
//						hotelRate = rate.getHotelRate();
//						totalRateTour = receive.subtract(comeinRate).subtract(hotelRate);
//					}
//				}
//			} else {
//				for(TopupRateCompany rate : compRates) {
//					if(receive.compareTo(rate.getMinPeriod()) < 0 && receive.compareTo(rate.getMaxPeriod()) > 0) {
//						comeinRate = rate.getComeinRate();
//						hotelRate = rate.getHotelRate();
//						totalRateTour = receive.subtract(comeinRate).subtract(hotelRate);
//					}					
//				}
//			}

			TourPayableNoteDto tour = new TourPayableNoteDto();
			tour.setTransactionDate(currentDate);
			tour.setReceive(receive);
			tour.setComeinRate(comeinRate.negate());
			tour.setHotelRate(hotelRate.negate());
			tour.setTotal(totalRateTour);
			response.setTourPayableNote(tour);
			
			if(entity.getHotels() != null) {
				HotelPayableNoteDto hotel = new HotelPayableNoteDto();
				hotel.setTransactionDate(currentDate);
				hotel.setHotelRate(hotelRate);
				hotel.setTotal(hotelRate);
				response.setHotelPayableNote(hotel);
			}
			
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}

	public void savePayableTourBookingByBookingCode(String bookingCode, PayableBookingDetailDto req){
		log.info("savePayableTourBookingByBookingCode bookingCode : {}", bookingCode);
		
		PayableBookingView entity = payableBookingViewRepository.findFirstByBookingCode(bookingCode);
		if(entity != null) {
			ReceivableNoteDto comeinNoteDto = req.getReceivableNote();
			TourPayableNoteDto tourNoteDto = req.getTourPayableNote();
			HotelPayableNoteDto hotelNoteDto = req.getHotelPayableNote();
			Long companyId = entity.getCompanyId();
			Long tourId = entity.getTourId();
			LocalDate tourDate = entity.getTourDate();
			String tourName = entity.getTourName();
			LocalDate paymentDate = entity.getPaymentDate();
			String paymentMethod = entity.getPaymentMethod();
			BigDecimal sellValue = entity.getTotalSellValue();
			String referenceName = entity.getReferenceName();
			
			//TODO get default account info (MOCK)
			String accountRefComeIn = "COMEIN_01";
			String accountRefHotel = "HOTEL_01";
			String accountRefTour = "TOUR_01";
			
			AccountingTransaction accountingTransaction = new AccountingTransaction();
			accountingTransaction.setCompanyId(companyId);
			accountingTransaction.setBookingCode(bookingCode);
			accountingTransaction.setTourDate(tourDate);
			accountingTransaction.setTourName(tourName);
			accountingTransaction.setPaymentDate(paymentDate);
			accountingTransaction.setPaymentMethod(paymentMethod);
			accountingTransaction.setSellValue(sellValue);
			accountingTransaction.setTourId(tourId);
			accountingTransaction.setReferenceName(referenceName);
			accountingTransaction = this.accountingTransactionRepository.save(accountingTransaction);
			long accountingTransactionId = accountingTransaction.getId();
			
			ReceiveTransaction receiveTransaction = new ReceiveTransaction();
			receiveTransaction.setAccountingId(accountingTransactionId);
			receiveTransaction.setDetail("Transaction receive by "+paymentMethod);
			receiveTransaction.setTransactionDate(paymentDate);
			receiveTransaction.setValue(sellValue);
			receiveTransaction.setAccountRef(accountRefComeIn);
			receiveTransaction.setNote(comeinNoteDto.getNote());
			this.receiveTransactionRepository.save(receiveTransaction);

			PayTransaction payTransaction = new PayTransaction();
			payTransaction.setAccountingId(accountingTransactionId);
			payTransaction.setDetail("Tour Rate");
			payTransaction.setTransactionDate(paymentDate);
			payTransaction.setValue(sellValue);
			payTransaction.setAccountRef(accountRefTour);
			payTransaction.setPayType("Tour");
			payTransaction.setNote(tourNoteDto.getNote());
			payTransaction = this.payTransactionRepository.save(payTransaction);
			long payTransactionId = payTransaction.getId();
			
			PayablePeriod payablePeriod = null;
			for(PayablePeriodDto dto : tourNoteDto.getPeriods()) {
				payablePeriod = new PayablePeriod();
				payablePeriod.setCompanyId(companyId);
				payablePeriod.setPayId(payTransactionId);
				payablePeriod.setPeriodType("Tour");
				payablePeriod.setDateFrom(dto.getFrom());
				payablePeriod.setDateTo(dto.getTo());
				payablePeriod.setPeriodDesc(DateUtils.toStr(dto.getFrom(), "dd/MMM/yyyy") + " - " + DateUtils.toStr(dto.getTo(), "dd/MMM/yyyy"));
				payablePeriod.setStatus("Open");
				this.payablePeriodRepository.save(payablePeriod);
			}

			if(hotelNoteDto != null && hotelNoteDto.getPeriods() != null && !hotelNoteDto.getPeriods().isEmpty()) {
				PayTransaction payTransactionHotel = new PayTransaction();
				payTransactionHotel.setAccountingId(accountingTransactionId);
				payTransactionHotel.setDetail("Hotel Rate");
				payTransactionHotel.setTransactionDate(paymentDate);
				payTransactionHotel.setValue(sellValue);
				payTransactionHotel.setAccountRef(accountRefHotel);
				payTransactionHotel.setPayType("Hotel");
				payTransactionHotel.setNote(hotelNoteDto.getNote());
				payTransactionHotel = this.payTransactionRepository.save(payTransactionHotel);
				long payTransactionHotelId = payTransactionHotel.getId();
				
				PayablePeriod payablePeriodHotel = null;
				for(PayablePeriodDto dto : tourNoteDto.getPeriods()) {
					payablePeriodHotel = new PayablePeriod();
					payablePeriodHotel.setCompanyId(companyId);
					payablePeriodHotel.setPayId(payTransactionHotelId);
					payablePeriodHotel.setPeriodType("Hotel");
					payablePeriodHotel.setDateFrom(dto.getFrom());
					payablePeriodHotel.setDateTo(dto.getTo());
					payablePeriodHotel.setPeriodDesc(DateUtils.toStr(dto.getFrom(), "dd/MMM/yyyy") + " - " + DateUtils.toStr(dto.getTo(), "dd/MMM/yyyy"));
					payablePeriodHotel.setStatus("Open");

					this.payablePeriodRepository.save(payablePeriodHotel);
				}
			}
			
			TourBooking tourBooking = this.tourBookingRepository.findFirstByBookingCodeAndStatus(bookingCode, Constant.STATUS_BOOKING_BOOKED);
			if(tourBooking != null) {
				tourBooking.setPayStatus(Constant.STATUS_PAY_PREPARE);
				
				this.tourBookingRepository.save(tourBooking);
			}
			
		} else {
			throw new ServiceException("Data not found.");
		}

		
	}
	
}

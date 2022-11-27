package com.r2dsolution.comein.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.TourCompanyRepository;
import com.r2dsolution.comein.dao.TourImageRepository;
import com.r2dsolution.comein.dao.TourInfoRepository;
import com.r2dsolution.comein.dao.TourInventoryRepository;
import com.r2dsolution.comein.dao.TourProvinceRepository;
import com.r2dsolution.comein.dao.TourTicketRepository;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.TourImageDto;
import com.r2dsolution.comein.dto.TourInfoDto;
import com.r2dsolution.comein.dto.TourInventoryDto;
import com.r2dsolution.comein.entity.TourCompany;
import com.r2dsolution.comein.entity.TourImage;
import com.r2dsolution.comein.entity.TourInfo;
import com.r2dsolution.comein.entity.TourInventory;
import com.r2dsolution.comein.entity.TourProvince;
import com.r2dsolution.comein.entity.TourTicket;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.TourInfoSpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.ObjectUtils;

@Service
public class TourInfoService {

	private static Logger log = LoggerFactory.getLogger(TourInfoService.class);
	
	@Autowired
	private TourInfoRepository tourInfoRepository;
	
	@Autowired
	private TourCompanyRepository tourCompanyRepository;
	
	@Autowired
	private TourImageRepository tourImageRepository;
	
	@Autowired
	private TourInventoryRepository tourInventoryRepository;
	
	@Autowired
	private TourTicketRepository tourTicketRepository;
	
	@Autowired
	private TourProvinceRepository tourProvinceRepository;
	
	@Value("${aws.s3.public.path}")
	private String s3PublicPath;

	@Value("${aws.s3.public.path.tour}")
	private String s3PublicPathTour;
	
	public ResponseListDto<TourInfoDto> searchTourInfo(String userToken, String email, String tourName, Pageable pageable){
		System.out.println("searchTourInfo email : "+email+", tourName : "+tourName);
		ResponseListDto<TourInfoDto> response = new ResponseListDto<>();
		
		TourCompany tourCompany = this.tourCompanyRepository.findFirstByOwnerId(userToken);
		if(tourCompany == null)
			throw new ServiceException("Data not found.");
		
		long companyId = tourCompany.getId();
		
		List<TourInfoDto> results = new ArrayList<>();
		TourInfo filter = new TourInfo();
		filter.setCompanyId(companyId);
		filter.setTourName(tourName);
		TourInfoSpecification spec = new TourInfoSpecification(filter);
		
		Sort sort = Sort.by(Direction.DESC, "updatedDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<TourInfo> entities = tourInfoRepository.findAll(spec, pageable1);
		
		TourInfoDto dto = null;
		for(TourInfo entity : entities) {
			dto = new TourInfoDto();
			dto.setId(entity.getId());
			dto.setCompanyId(entity.getCompanyId());
			dto.setTourName(entity.getTourName());
			dto.setTourDesc(entity.getTourDesc());
			dto.setStartDate(entity.getStartDate());
			dto.setEndDate(entity.getEndDate());
			dto.setStatus(entity.getStatus());
		    
			results.add(dto);
		}
		
		response.setDatas(results);
		
		Pageable page = entities.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), entities.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public TourInfoDto getTourInfo(long id){
		System.out.println("getTourInfo id : "+id);
		TourInfoDto response = null;
		
		Optional<TourInfo> entities = tourInfoRepository.findById(id);
		if(entities.isPresent()) {
			
			TourInfo entity = entities.get();
		
			response = new TourInfoDto();
			response.setId(entity.getId());
			response.setCompanyId(entity.getCompanyId());
			response.setTourName(entity.getTourName());
			response.setTourDesc(entity.getTourDesc());
			response.setStartDate(entity.getStartDate());
			response.setEndDate(entity.getEndDate());
			response.setCountry(entity.getCountry());
//			response.setProvince(entity.getProvince());
			response.setDetail(entity.getDetail());
			response.setStatus(entity.getStatus());
			
			Optional<TourCompany> company = tourCompanyRepository.findById(entity.getCompanyId());
			if(company.isPresent()) {
				response.setCompanyName(company.get().getCompanyName());
			}
			
			List<TourImage> imageEntities = tourImageRepository.findByTourIdOrderBySeq(entity.getId());
			if(!imageEntities.isEmpty()) {
				List<TourImageDto> imageDtos = new LinkedList<>();
				TourImageDto dto = null;
				String tourUrl = this.s3PublicPath + this.s3PublicPathTour;
				for(TourImage image : imageEntities) {
					dto = new TourImageDto();
					dto.setId(image.getId());
					dto.setTourId(image.getTourId());
					dto.setImgType(image.getImgType());
					dto.setSeq(image.getSeq());
					dto.setTourImg(image.getTourImg());
					if(!ObjectUtils.isEmpty(image.getTourImg()))
						dto.setImgUrl(tourUrl + image.getTourImg());
					dto.setStatus(image.getStatus());
				    
					imageDtos.add(dto);
				}
				response.setImages(imageDtos);
			}
			
			List<TourProvince> provinceEntities = tourProvinceRepository.findByTourIdOrderByProvince(entity.getId());
			if(!provinceEntities.isEmpty()) {
				List<String> provinces = new LinkedList<>();
				for(TourProvince province : provinceEntities) {
					provinces.add(province.getProvince());
				}
				response.setProvinces(provinces);
			}

		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public List<TourInfoDto> saveTourInfo(TourInfoDto req, String userToken){
		log.info("Start saveTourInfo tourName : {}", req.getTourName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getCompanyId())) {
				throw new ServiceException("Tour Company is require.");	
			}
			if(ObjectUtils.isEmpty(req.getTourName())) {
				throw new ServiceException("Tour Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getStartDate())) {
				throw new ServiceException("Start Date is require.");	
			}
			if(ObjectUtils.isEmpty(req.getEndDate())) {
				throw new ServiceException("End Date is require.");	
			}
		}
		
		List<TourInfoDto> results = new ArrayList<>();
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		TourInfo entity = new TourInfo();
		entity.setCompanyId(req.getCompanyId());
		entity.setTourName(req.getTourName());
		entity.setTourDesc(req.getTourDesc());
		entity.setStartDate(req.getStartDate());
		entity.setEndDate(req.getEndDate());
		entity.setCountry(req.getCountry());
//		entity.setProvince(req.getProvince());
		entity.setDetail(req.getDetail());
		entity.setStatus(Constant.STATUS_VERIFY);
		entity.setCreatedDate(currentTimestamp);
		entity.setCreatedBy(userToken);
		entity.setUpdatedDate(currentTimestamp);
		entity.setUpdatedBy(userToken);
		
		tourInfoRepository.save(entity);
		
		if(req.getImages() != null && !req.getImages().isEmpty()) {
			
			List<TourImage> imageEntities = new ArrayList<>();
			TourImage imageEntity = null;
			for(TourImageDto image : req.getImages()) {
				imageEntity = new TourImage();
				imageEntity.setTourId(entity.getId());
				imageEntity.setImgType(Constant.IMG_TYPE_COVER);
				imageEntity.setSeq(image.getSeq());
				imageEntity.setTourImg(image.getTourImg());
				imageEntity.setStatus(Constant.STATUS_VERIFY);
				
				imageEntities.add(imageEntity);
			}
			
			tourImageRepository.saveAll(imageEntities);
		}
		
		if(req.getProvinces() != null && !req.getProvinces().isEmpty()) {
			
			List<TourProvince> provinceEntities = new ArrayList<>();
			TourProvince provinceEntity = null;
			for(String province : req.getProvinces()) {
				provinceEntity = new TourProvince();
				provinceEntity.setTourId(entity.getId());
				provinceEntity.setProvince(province);

				provinceEntities.add(provinceEntity);
			}
			
			tourProvinceRepository.saveAll(provinceEntities);
		}
		
		return results;
	}

	
	public List<TourInfoDto> updateTourInfo(long id, TourInfoDto req, String userToken){
		log.info("Start updateTourInfo id : {}, tourName : {}", id, req.getTourName());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getCompanyId())) {
				throw new ServiceException("Tour Company is require.");	
			}
			if(ObjectUtils.isEmpty(req.getTourName())) {
				throw new ServiceException("Tour Name is require.");	
			}
			if(ObjectUtils.isEmpty(req.getStartDate())) {
				throw new ServiceException("Start Date is require.");	
			}
			if(ObjectUtils.isEmpty(req.getEndDate())) {
				throw new ServiceException("End Date is require.");	
			}
		}
		
		List<TourInfoDto> results = new ArrayList<>();
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		Optional<TourInfo> entities = tourInfoRepository.findById(id);
		if(entities.isPresent()) {
			TourInfo entity = entities.get();
			entity.setTourName(req.getTourName());
			entity.setTourDesc(req.getTourDesc());
			entity.setStartDate(req.getStartDate());
			entity.setEndDate(req.getEndDate());
			entity.setCountry(req.getCountry());
//			entity.setProvince(req.getProvince());
			entity.setDetail(req.getDetail());
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			tourInfoRepository.save(entity);
			
			if(req.getImages() != null && !req.getImages().isEmpty()) {
				
				tourImageRepository.deleteByTourId(entity.getId());
				
				List<TourImage> imageEntities = new ArrayList<>();
				TourImage imageEntity = null;
				for(TourImageDto image : req.getImages()) {
					imageEntity = new TourImage();
					imageEntity.setTourId(entity.getId());
					imageEntity.setImgType("Cover");
					imageEntity.setSeq(image.getSeq());
					imageEntity.setTourImg(image.getTourImg());
					imageEntity.setStatus(Constant.STATUS_VERIFY);
					
					imageEntities.add(imageEntity);
				}
				
				tourImageRepository.saveAll(imageEntities);
			}
			
			if(req.getProvinces() != null && !req.getProvinces().isEmpty()) {
				
				tourProvinceRepository.deleteByTourId(entity.getId());
				
				List<TourProvince> provinceEntities = new ArrayList<>();
				TourProvince provinceEntity = null;
				for(String province : req.getProvinces()) {
					provinceEntity = new TourProvince();
					provinceEntity.setTourId(entity.getId());
					provinceEntity.setProvince(province);

					provinceEntities.add(provinceEntity);
				}
				
				tourProvinceRepository.saveAll(provinceEntities);
			}
		}
		
		return results;
	}
	
	public List<TourInventoryDto> getTourInventoryInfo(Long tourId, LocalDate startDate, LocalDate endDate){
		System.out.println("getTourInventoryInfo startDate : "+startDate+", endDate : "+endDate);
		List<TourInventoryDto> response = new LinkedList<>();
		
		List<TourInventory> entities = this.tourInventoryRepository.findByTourIdAndTourDateGreaterThanEqualAndTourDateLessThanEqualAndStatusOrderByTourDateAsc(tourId, startDate, endDate, Constant.STATUS_VERIFY);
		
		TourInventoryDto dto = null;
		for(TourInventory entity : entities) {
			dto = new TourInventoryDto();
			dto.setId(entity.getId());
			dto.setTourId(entity.getTourId());
			dto.setTourDate(entity.getTourDate());
			dto.setTotal(entity.getTotal());
			dto.setAdultRate(entity.getAdultRate());
			dto.setChildRate(entity.getChildRate());
			dto.setCancelable(entity.getCancelable());
			dto.setCancelBefore(entity.getCancelBefore());
			dto.setStatus(entity.getStatus());
		    
			response.add(dto);
		}
		
		return response;
	}
	
	public void saveTourInventory(String userToken, Long tourId, TourInventoryDto req){
		log.info("Start saveTourInventoryInfo tourId : {}, startDate : {}, endDate : {}, tourDate : {}", tourId, req.getStartDate(), req.getEndDate(), req.getTourDate());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(tourId)) {
				throw new ServiceException("Tour ID is require.");	
			}
			if(ObjectUtils.isEmpty(req.getStartDate()) && ObjectUtils.isEmpty(req.getEndDate()) && ObjectUtils.isEmpty(req.getTourDate())) {
				throw new ServiceException("Tour Date is require.");	
			}
			if(req.getTotal() == 0) {
				throw new ServiceException("Total Inventory is require.");	
			}
			if(ObjectUtils.isEmpty(req.getAdultRate())) {
				throw new ServiceException("Adult Rate is require.");	
			}
			if(ObjectUtils.isEmpty(req.getChildRate())) {
				throw new ServiceException("Child Rate is require.");	
			}
			if(!ObjectUtils.isEmpty(req.getCancelable()) && "Y".equals(req.getCancelable()) && req.getCancelBefore() == 0) {
				throw new ServiceException("Day Cancel Before is require.");	
			}
		}
		
		if(req.getTourDate() != null) {
			generateTourInventory(tourId, req.getTourDate(), userToken, req);
		} else {
			LocalDate startDate = req.getStartDate();
			LocalDate endDate = req.getEndDate();
			while(!endDate.isBefore(startDate)) {
				
				int cntDup = this.tourInventoryRepository.countByTourIdAndTourDate(tourId, startDate);
				if(cntDup > 0) {
					throw new ServiceException("Tour Date [" + startDate.format(DateTimeFormatter.ISO_DATE) + "] is duplicate.");	
				}
				
				generateTourInventory(tourId, startDate, userToken, req);
				
				startDate = startDate.plusDays(1);
			}
		}
		
	}
	
	private void generateTourInventory(long tourId, LocalDate tourDate, String userToken, TourInventoryDto req) {
		log.info("Start generateTourInventory tourId : {}, tourDate : {}", tourId, req.getTourDate());
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		long totalInventory = req.getTotal();
		
		TourInventory entity = new TourInventory();
		entity.setTourId(tourId);
		entity.setTourDate(tourDate);
		entity.setTotal(totalInventory);
		entity.setAdultRate(req.getAdultRate());
		entity.setChildRate(req.getChildRate());
		entity.setCancelable(req.getCancelable());
		entity.setCancelBefore(req.getCancelBefore());
		entity.setStatus(Constant.STATUS_VERIFY);
		entity.setCreatedDate(currentTimestamp);
		entity.setCreatedBy(userToken);
		entity.setUpdatedDate(currentTimestamp);
		entity.setUpdatedBy(userToken);
		
		TourInventory tourInventory = this.tourInventoryRepository.save(entity);
		long inventoryId = tourInventory.getId();

		CompletableFuture.runAsync(() -> {
			generateTourTicket(inventoryId, tourDate, userToken, req);
		});
		log.info("End generateTourInventory tourId : {}, tourDate : {}", tourId, req.getTourDate());
		
	}
	
	private void generateTourTicket(Long inventoryId, LocalDate tourDate, String userToken, TourInventoryDto req) {
		log.info("Start generateTourTicket inventoryId : {}, tourDate : {}", inventoryId, req.getTourDate());
		LocalDateTime currentTimestamp = LocalDateTime.now();
		List<TourTicket> tickets = new ArrayList<>();
		TourTicket ticket = null;
		String ticketCode = null;
		long totalInventory = req.getTotal();
		for(long i=0; i<totalInventory; i++) {
			ticketCode = org.apache.commons.lang.StringUtils.leftPad(""+inventoryId, 10, "0") + org.apache.commons.lang.StringUtils.leftPad(""+(i+1), 5, "0");
			ticket = new TourTicket();
			ticket.setInventoryId(inventoryId);
			ticket.setTicketCode(ticketCode);
			ticket.setTourDate(tourDate);
			ticket.setAdultRate(req.getAdultRate());
			ticket.setChildRate(req.getChildRate());
			ticket.setCancelable(req.getCancelable());
			ticket.setCancelBefore(req.getCancelBefore());
			ticket.setStatus(Constant.STATUS_TICKET_ACTIVE);
			ticket.setCreatedDate(currentTimestamp);
			ticket.setCreatedBy(userToken);
			
			tickets.add(ticket);
		}
		
		this.tourTicketRepository.saveAll(tickets);		
		log.info("End generateTourTicket inventoryId : {}, tourDate : {}", inventoryId, req.getTourDate());
		
	}

	public void updateTourInventory(String userToken, Long tourId, TourInventoryDto req){
		log.info("Start updateTourInventory tourId : {}, tourDate : {}", tourId, req.getStartDate(), req.getEndDate(), req.getTourDate());
		
		long inventoryId = 0L;
		boolean isCancelTicket = false;
		if(req != null && req.getTotal() == 0) {
			isCancelTicket = true;
			inventoryId = req.getId();
		}
		
		if(isCancelTicket) {
			Optional<TourInventory> opt = this.tourInventoryRepository.findById(inventoryId);
			if(opt.isPresent()) {
				LocalDateTime currentTimestamp = LocalDateTime.now();
				
				TourInventory tourInventory = opt.get();
				tourInventory.setStatus(Constant.STATUS_CANCEL);
				tourInventory.setUpdatedBy(userToken);
				tourInventory.setUpdatedDate(currentTimestamp);
				this.tourInventoryRepository.save(tourInventory);
			}
			this.tourTicketRepository.disableTicketByInventory(Constant.STATUS_TICKET_INACTIVE, inventoryId);
		} else {
			if(req != null) {
				if(ObjectUtils.isEmpty(tourId)) {
					throw new ServiceException("Tour ID is require.");	
				}
				if(ObjectUtils.isEmpty(req.getId())) {
					throw new ServiceException("Tour Inventory ID is require.");	
				}			
				if(ObjectUtils.isEmpty(req.getTourDate())) {
					throw new ServiceException("Tour Date is require.");	
				}
				if(ObjectUtils.isEmpty(req.getAdultRate())) {
					throw new ServiceException("Adult Rate is require.");	
				}
				if(ObjectUtils.isEmpty(req.getChildRate())) {
					throw new ServiceException("Child Rate is require.");	
				}
				if(!ObjectUtils.isEmpty(req.getCancelable()) && "Y".equals(req.getCancelable()) && req.getCancelBefore() == 0) {
					throw new ServiceException("Day Cancel Before is require.");	
				}
			}
		
			Optional<TourInventory> entityOpt = this.tourInventoryRepository.findById(inventoryId);
			if(entityOpt.isPresent()) {
				LocalDateTime currentTimestamp = LocalDateTime.now();
				
				TourInventory entity = entityOpt.get();
				entity.setTotal(req.getTotal());
				entity.setAdultRate(req.getAdultRate());
				entity.setChildRate(req.getChildRate());
				entity.setCancelable(req.getCancelable());
				entity.setCancelBefore(req.getCancelBefore());
				entity.setUpdatedBy(userToken);
				entity.setUpdatedDate(currentTimestamp);
				
				this.tourInventoryRepository.save(entity);
				
				//re-generate tour ticket
				this.tourTicketRepository.disableTicketByInventory(Constant.STATUS_TICKET_INACTIVE, inventoryId);
				
				generateTourTicket(inventoryId, req.getTourDate(), userToken, req);
				
			}
		}
	}
}

package com.r2dsolution.comein.dto;

public class PayableBookingDetailDto {
	
	private ReceivableNoteDto receivableNote;
	private TourPayableNoteDto tourPayableNote;
	private HotelPayableNoteDto hotelPayableNote;
	
	public ReceivableNoteDto getReceivableNote() {
		return receivableNote;
	}
	public void setReceivableNote(ReceivableNoteDto receivableNote) {
		this.receivableNote = receivableNote;
	}
	public TourPayableNoteDto getTourPayableNote() {
		return tourPayableNote;
	}
	public void setTourPayableNote(TourPayableNoteDto tourPayableNote) {
		this.tourPayableNote = tourPayableNote;
	}
	public HotelPayableNoteDto getHotelPayableNote() {
		return hotelPayableNote;
	}
	public void setHotelPayableNote(HotelPayableNoteDto hotelPayableNote) {
		this.hotelPayableNote = hotelPayableNote;
	}
	
	
    
}

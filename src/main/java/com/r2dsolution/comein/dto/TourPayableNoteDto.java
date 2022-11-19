package com.r2dsolution.comein.dto;

import java.math.BigDecimal;

public class TourPayableNoteDto extends PayableNoteDto{

    private BigDecimal receive;
    private BigDecimal comeinRate;
    
	public BigDecimal getComeinRate() {
		return comeinRate;
	}
	public void setComeinRate(BigDecimal comeinRate) {
		this.comeinRate = comeinRate;
	}
	public BigDecimal getReceive() {
		return receive;
	}
	public void setReceive(BigDecimal receive) {
		this.receive = receive;
	}
}

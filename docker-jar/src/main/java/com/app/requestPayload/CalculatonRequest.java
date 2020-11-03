package com.app.requestPayload;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CalculatonRequest {

	@NotNull(message = "Rate Value can not null")
	@Max(value = 100,message = "less than 100")
	@Min(value = 1)
	private double rate;
	
	@NotNull(message = "Price Value can not null")
	@Max(value = 100,message = "less than 100")
	@Min(value = 1)
	private double price;
	
	@NotNull(message = "Year Value can not null")
	@Max(value = 100,message = "less than 100")
	@Min(value = 1)
	private int year;
	
}

package com.app.model;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkRoom {
	    private int id;
	    private String emailId;
	    private String username;	    
	    private Long roomRentTotal;	    
	    private String bathRoomStatus;	    
	    private String previousbathRoomStatus;	    
	    private Date lastMarketGone; 
	    private Map<String,Long> sheetData;
}
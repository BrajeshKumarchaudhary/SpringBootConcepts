package com.app.jpa.model;

import java.util.Calendar;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "user_profile")
@Data
@AllArgsConstructor
public class UserModel {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	private int age;
	@NotNull
	private String addr;
	
	@CreationTimestamp
	private Calendar created_at;
	
	@UpdateTimestamp
	private Calendar updated_at;
}

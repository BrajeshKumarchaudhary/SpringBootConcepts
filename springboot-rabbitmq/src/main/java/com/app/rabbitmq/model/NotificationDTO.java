package com.app.rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
@AllArgsConstructor
public class NotificationDTO {
	private String message;
	private String customerName;
}

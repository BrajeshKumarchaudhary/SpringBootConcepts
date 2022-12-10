package com.app.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rabbitmq.model.NotificationDTO;
import com.app.rabbitmq.publisher.MessagePublisher;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "rabbitmq/direct/")
@ApiOperation(tags = "DirectExchangeController", value = "Distrubute Message through DirectExchange")
public class RabbitMqDirectController {
	Logger logger = LoggerFactory.getLogger(RabbitMqDirectController.class);
	@Autowired
	private MessagePublisher messagePublisher;

	@PostMapping(value = "/producer")
	public String producer(@Validated @RequestBody(required = true) NotificationDTO message) {
		if (ObjectUtils.isEmpty(message)) {
			return "Recieved Empty Message";
		}
           messagePublisher.sendMessageToQueue(message);
		return "Message sent to the RabbitMQ Successfully";
	}

}

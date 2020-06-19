package com.app.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.rabbitmq.model.Notification;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "rabbitmq/direct/")
@ApiOperation(tags = "DirectExchangeController", value = "Distrubute Message through DirectExchange")
public class RabbitMqDirectController {
	Logger logger = LoggerFactory.getLogger(RabbitMqDirectController.class);
	@Autowired
	private AmqpTemplate amqpTemplate;

	@GetMapping(value = "/producer")
	public String producer(@Validated @RequestBody(required = true) Notification message) {
		if (message != null) {
			amqpTemplate.convertAndSend(message.getExchangeName(), message.getRoutingKey(), message.getMessage());
		} else {
			logger.info("Please Provide valid RequestBody");
		}

		return "Message sent to the RabbitMQ Successfully";
	}

}

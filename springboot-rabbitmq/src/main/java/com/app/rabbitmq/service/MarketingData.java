package com.app.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Fixed;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.app.rabbitmq.model.Notification;



@Service
public class MarketingData {
	Logger logger = LoggerFactory.getLogger(MarketingData.class);
	@Autowired
	private AmqpTemplate amqpTemplate;
	

	
//	@Scheduled(fixedRate = 1000)
	public void publishMarketingdata()
	{
		Notification obj=new Notification();
		obj.setExchangeName("direct-exchange");
		obj.setRoutingKey("marketing");
		obj.setMessage("Hello Brajesh Kumar I m java developer");
		amqpTemplate.convertAndSend(obj.getExchangeName(), obj.getRoutingKey(), obj.getMessage());
	}
}

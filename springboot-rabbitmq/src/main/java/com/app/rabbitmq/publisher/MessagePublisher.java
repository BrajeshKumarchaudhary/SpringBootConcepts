package com.app.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.app.rabbitmq.controller.RabbitMqDirectController;
import com.app.rabbitmq.model.NotificationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessagePublisher {
	Logger logger = LoggerFactory.getLogger(MessagePublisher.class);
	@Autowired
	@Qualifier("messageDeliveryTemplate")
	RabbitTemplate rabbitTemplate;
	@Autowired
	ObjectMapper objectMapper;
	
	private String exchange="message.exchange";
	private String routing_key="message.key";

	public void sendMessageToQueue(NotificationDTO notificationDto) {
		final Message message = convertToMessage(notificationDto);
		rabbitTemplate.convertAndSend(exchange, routing_key, message);
	}

	private Message convertToMessage(NotificationDTO notificationDto) {
		Message message = null;
		try {
			final String messageJson = objectMapper.writeValueAsString(notificationDto);
			// We can also set RequestHeader here
			message = MessageBuilder.withBody(messageJson.getBytes())
					.setContentType(MessageProperties.CONTENT_TYPE_JSON).build();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

}

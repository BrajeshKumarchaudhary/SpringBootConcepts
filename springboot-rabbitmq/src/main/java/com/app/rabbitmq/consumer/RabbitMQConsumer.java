package com.app.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.rabbitmq.model.NotificationDTO;
import com.app.rabbitmq.publisher.MessagePublisher;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQConsumer {
	Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@Autowired
	ObjectMapper objectMapper;

	@RabbitListener(queues = "message.queue")
	public void consumeMessage(final Message eventMessage) {
	
		if (ObjectUtils.isEmpty(eventMessage)) {
			logger.info("Message Empty");
			return;
		}
		final String message = new String(eventMessage.getBody());
		try {
			NotificationDTO notificationDTO = objectMapper.readValue(message, NotificationDTO.class);
			logger.info("Received Event Message {}", notificationDTO.getMessage());

		} catch (Exception e) {
                e.printStackTrace();
		}
	}

}

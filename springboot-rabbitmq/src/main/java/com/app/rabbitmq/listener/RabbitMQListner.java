package com.app.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListner implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("Consuming Message - " + new String(message.getBody()));
	}
		
	
	@RabbitListener(queues = "marketingQueue")
	public void listen(String in) {
	    System.out.println("Message read from marketingQueue : " + in);
	}

}

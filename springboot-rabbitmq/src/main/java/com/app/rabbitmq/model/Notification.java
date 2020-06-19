package com.app.rabbitmq.model;

import lombok.Data;

@Data
public class Notification {
  private String exchangeName;
  private String routingKey;
  private String message;
}

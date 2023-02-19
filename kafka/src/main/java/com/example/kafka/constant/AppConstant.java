package com.example.kafka.constant;

public enum AppConstant {
    DEMO_TOPIC("DEMO_TOPIC");
    private String topicName;

    AppConstant(String topic) {
        this.topicName = topic;
    }
    public String getTopicName(){
        return this.topicName;
    }
}

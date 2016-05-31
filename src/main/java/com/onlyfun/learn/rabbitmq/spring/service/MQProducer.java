package com.onlyfun.learn.rabbitmq.spring.service;

/**
 * Created by jareddu on 16-5-31.
 */
public interface MQProducer {
    /**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String exchange,String routingkey, Object object);
}

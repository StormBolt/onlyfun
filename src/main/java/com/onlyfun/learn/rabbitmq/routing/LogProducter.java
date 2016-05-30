package com.onlyfun.learn.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by jareddu on 16-5-30.
 */
public class LogProducter{
    private static final Logger logger = Logger.getLogger(LogProducter.class);
    private static final boolean durable = true;//消息队列持久化
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String exchange_name;
    private String routing_key;

    public void exchangeDeclare(String exchange_name, String routing_key,String msg) {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(exchange_name, "direct", durable);
            channel.basicPublish(exchange_name, routing_key, null, msg.getBytes());
            logger.info("push msg ["+ msg +" ] to exchange [" + exchange_name + "] routing [" + routing_key + "] success ");
        }catch (Exception e){
            logger.error("LogProducter error ....");
            e.printStackTrace();
        }

    }

}

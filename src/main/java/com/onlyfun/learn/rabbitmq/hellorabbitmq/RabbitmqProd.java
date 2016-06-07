package com.onlyfun.learn.rabbitmq.hellorabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * Created by jareddu on 16-5-27.
 */
public class RabbitmqProd{
    Logger logger = Logger.getLogger(RabbitmqProd.class);
    RabbitFactory factory;

    public RabbitmqProd(RabbitFactory factory) {
        this.factory = factory;
    }

    public void sendMsg(String message) throws IOException{
        logger.info(" [x] SEND '" + message + "'");
        Channel channel = factory.getChannel();
        channel.basicPublish("",factory.getTopic(),null,message.getBytes());
    }

}

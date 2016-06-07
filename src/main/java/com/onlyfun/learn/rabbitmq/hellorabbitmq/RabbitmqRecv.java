package com.onlyfun.learn.rabbitmq.hellorabbitmq;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by jareddu on 16-5-27.
 */
public class RabbitmqRecv {
    Logger logger = Logger.getLogger(RabbitmqRecv.class);
    RabbitFactory factory;

    public RabbitmqRecv(RabbitFactory factory) {
        this.factory = factory;
    }

    public void recvMsg(String topic) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
        Channel channel = factory.getChannel();
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(topic, true, consumer);
        while(true){
        	QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        	String message = new String(delivery.getBody());
        	logger.info(" [x] RECEIVED '" + message + "'");
        }
    }
}

package com.onlyfun.learn.rabbitmq;

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

    public void recvMsg(String topic) throws IOException{
        Channel channel = factory.getChannel();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                logger.info(" [x] received '" + message + "'");
            }
        };
        channel.basicConsume(topic, true, consumer);
    }
}

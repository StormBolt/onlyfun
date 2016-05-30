package com.onlyfun.learn.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by jareddu on 16-5-30.
 */
public class LogConsumer implements Runnable {
    private static final Logger logger = Logger.getLogger(LogConsumer.class);
    private String exchange_name;
    private String routing_key;
    private String queue_name;

    public LogConsumer(String exchange_name, String routing_key, String queue_name) {
        this.exchange_name = exchange_name;
        this.routing_key = routing_key;
        this.queue_name = queue_name;
    }

    @Override
    public void run() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange_name, "direct", true);
            channel.queueDeclare(queue_name, true, false, false, null);
            channel.basicQos(1);
            channel.queueBind(queue_name, exchange_name, routing_key);
            logger.info("consumer 队列" + queue_name + "绑定 routing_key [" + routing_key + "] 成功！");
            receive(channel, queue_name);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void receive(Channel channel,String QUEUE_NAME) throws Exception {
        // 声明消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody(), "UTF-8");
            logger.info(">>>>>>" + QUEUE_NAME + " Received '" + message + "'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}

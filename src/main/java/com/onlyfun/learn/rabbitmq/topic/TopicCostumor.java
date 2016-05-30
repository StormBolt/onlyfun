package com.onlyfun.learn.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jareddu on 16-5-30.
 */
public class TopicCostumor implements Runnable {
    private  static Logger logger = Logger.getLogger(TopicCostumor.class);
    private String host;
    private String exchange_name;
    private String routing_key;
    private String queue_name;
    private Boolean durable;

    public TopicCostumor(String host, String exchange_name, String routing_key, String queue_name, Boolean durable) {
        this.host = host;
        this.exchange_name = exchange_name;
        this.routing_key = routing_key;
        this.queue_name = queue_name;
        this.durable = durable;
    }

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange_name, "topic", durable);
            channel.queueDeclare(queue_name, true, false, false, null);
            channel.basicQos(1);
            channel.queueBind(queue_name, exchange_name, routing_key);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queue_name, true, consumer);

            while (true)
            {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                String routingKey = delivery.getEnvelope().getRoutingKey();
                logger.info("queue_name " + queue_name + " [x] Received routingKey = " + routingKey + ",msg = " + message + ".");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

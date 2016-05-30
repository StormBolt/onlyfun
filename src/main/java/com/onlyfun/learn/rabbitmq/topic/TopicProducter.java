package com.onlyfun.learn.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jareddu on 16-5-30.
 */
public class TopicProducter {
    private static Logger logger = Logger.getLogger(TopicProducter.class);

    Connection connection;
    Channel channel;

    public TopicProducter init(String host){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        try {
            this.connection = factory.newConnection();
            this.channel = connection.createChannel();
        }catch(TimeoutException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /***
     * push msg to rabbitmq
     *
     * @param type mq type
     * @param exchange_name exchange name
     * @param routing_key routing key
     * @param durable whether persistent
     * @param msg msg info
     */
    public void pulish(String type,String exchange_name,String routing_key,boolean durable,String msg){
        try{
            channel.exchangeDeclare(exchange_name,type, durable);
            channel.basicPublish(exchange_name, routing_key, null, msg
                    .getBytes());
            logger.info("msg [ " + msg + " ] push exchange " + exchange_name
                    + " routing " + routing_key  + " success ");
        }catch (IOException e) {
            logger.error("msg [ " + msg + " ] push exchange " + exchange_name
                    + " routing " + routing_key  + " fail ");
            e.printStackTrace();

        }


    }

}

package com.onlyfun.learn.rabbitmq.Subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by jareddu on 16-5-30.
 */
public class EmitLog implements Runnable {
    Logger logger = Logger.getLogger(EmitLog.class);

    private final static String EXCHANGE_NAME = "ex_log";

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String message = new Date().toLocaleString()+" : log something";
            for (int i = 0; i < 20 ; i++){
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
                TimeUnit.SECONDS.sleep(1);
                logger.info(" [x] Sent ...'" + message + "'");
            }
            channel.close();
            connection.close();
        }catch (Exception e){
            logger.error("publish info error ...");
            e.printStackTrace();
        }
    }
}

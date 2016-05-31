package com.onlyfun.learn.rabbitmq.spring.service;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by jareddu on 16-5-31.
 */
@Component
public class QueueListenter implements MessageListener {
    private static Logger logger = Logger.getLogger(QueueListenter.class);
    @Override
    public void onMessage(Message message) {
        try{
            String msg = new String(message.getBody(), "UTF-8");
            logger.info("*************** receive messeage : " + msg);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

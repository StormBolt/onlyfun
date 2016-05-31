package com.onlyfun.learn.rabbitmq.spring.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jareddu on 16-5-31.
 */
@Service
public class MQProducerImpl implements  MQProducer{
    private static Logger logger = Logger.getLogger(MQProducerImpl.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);
    /* (non-Javadoc)
     * @see com.stnts.tita.rm.api.mq.MQProducer#sendDataToQueue(java.lang.String, java.lang.Object)
     */
    @Override
    public void sendDataToQueue(String exchange,String routingkey, Object object) {
        try {
            amqpTemplate.convertAndSend(exchange,routingkey,object);
            logger.info("*************** send messeage to (" + exchange + ") routing (" + routingkey + ") [ " + JSON.toJSON(object) + " ]" );
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}

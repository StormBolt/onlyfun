package com.onlyfun.learn.rabbitmq.hellorabbitmq;

import org.apache.log4j.Logger;

/**
 * Created by jareddu on 16-5-27.
 */
public class TestRabbitMQ {
	Logger logger = Logger.getLogger(TestRabbitMQ.class);

    public static void main(String[] args) {
        RabbitFactory factory = new RabbitFactory("ttest");
        RabbitmqProd prod = new RabbitmqProd(factory);
        RabbitmqRecv recv = new RabbitmqRecv(factory);
        try {
        	prod.sendMsg("hello rabbit");  
        	recv.recvMsg("ttest");
            factory.close();
        }catch (Exception e){
        	e.printStackTrace();
        }
    }

}

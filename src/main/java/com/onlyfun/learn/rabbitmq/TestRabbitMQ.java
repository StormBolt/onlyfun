package com.onlyfun.learn.rabbitmq;

/**
 * Created by jareddu on 16-5-27.
 */
public class TestRabbitMQ {

    public static void main(String[] args) {
        RabbitFactory factory = new RabbitFactory("msg_test");
        RabbitmqProd prod = new RabbitmqProd(factory);
        RabbitmqRecv recv = new RabbitmqRecv(factory);
        try {
            prod.sendMsg("hello rabbit");
            recv.recvMsg("msg_test");
            factory.close();
        }catch (Exception e){

        }
    }

}

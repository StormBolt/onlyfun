package com.onlyfun.learn.rabbitmq.routing;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jareddu on 16-5-30.
 */
public class TestRabbitMQ {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LogProducter producter = new LogProducter();

        for (int i = 0; i < 5; i ++){
            executorService.submit(new LogConsumer("test_exchange","exchange1","queue_test_exchange_" + i));
        }
        for (int i = 0; i < 10; i++){
            producter.exchangeDeclare("test_exchange","exchange1","msg " + i);
        }
    }
}

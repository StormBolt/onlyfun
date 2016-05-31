package com.onlyfun.learn.rabbitmq.subscribe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jareddu on 16-5-30.
 */
public class TestRabbitMQ {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new EmitLog());
        executorService.submit(new ReceiveLogsToConsole());
        executorService.submit(new ReceiveLogsToSave());
        executorService.shutdown();

    }
}

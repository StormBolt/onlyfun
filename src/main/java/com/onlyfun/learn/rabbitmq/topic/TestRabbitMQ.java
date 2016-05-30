package com.onlyfun.learn.rabbitmq.topic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jareddu on 16-5-30.
 */
public class TestRabbitMQ {
    public static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        executorService.submit(new TopicCostumor("localhost","test_topic","red.*","red_rev_msg",true));
        executorService.submit(new TopicCostumor("localhost","test_topic","green.*","green_rev_msg",true));
        executorService.submit(new TopicCostumor("localhost","test_topic","*.glass","glass_rev_msg",true));
        executorService.submit(new TopicCostumor("localhost","test_topic","*.water","water_rev_msg",true));

        new TopicProducter().init("localhost").pulish("topic","test_topic","red.water",true,"water is red");
        new TopicProducter().init("localhost").pulish("topic","test_topic","red.glass",true,"glass is red");
        new TopicProducter().init("localhost").pulish("topic","test_topic","green.water",true,"water is green");
        new TopicProducter().init("localhost").pulish("topic","test_topic","green.glass",true,"glass is green");
        new TopicProducter().init("localhost").pulish("topic","test_topic","green.sky",true,"sky is green");
    }

}

package com.onlyfun.learn.rabbitmq.Subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.log4j.Logger;

/**
 * Created by jareddu on 16-5-30.
 */
public class ReceiveLogsToConsole implements Runnable{
    private final static String EXCHANGE_NAME = "ex_log";
    Logger logger = Logger.getLogger(ReceiveLogsToConsole.class);

    @Override
    public void run() {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            logger.info(" [*] Waiting for messages. To exit press CTRL+C");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            // 指定接收者，第二个参数为自动应答，无需手动应答
            channel.basicConsume(queueName, true, consumer);

            while (true)
            {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                logger.info(" [x] Received '" + message + "'");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

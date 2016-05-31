package com.onlyfun.learn.rabbitmq.subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jareddu on 16-5-30.
 */
public class ReceiveLogsToSave implements Runnable{
    private final static String EXCHANGE_NAME = "ex_log";
    Logger logger = Logger.getLogger(ReceiveLogsToSave.class);
    @Override
    public void run() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Thread.sleep(1000);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind("queue_name",EXCHANGE_NAME,"");
            logger.info(" [*] Waiting for messages. To exit press CTRL+C");
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, true, consumer);
            while (true)
            {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                print2File(message);
            }
        }catch (Exception e){

        }
    }

    private void print2File(String msg)
    {
        try
        {
            String dir = ReceiveLogsToSave.class.getClassLoader().getResource("").getPath();
            String logFileName = new SimpleDateFormat("yyyy-MM-dd")
                    .format(new Date());
            File file = new File(dir, logFileName+".txt");
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write((msg + "\r\n").getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

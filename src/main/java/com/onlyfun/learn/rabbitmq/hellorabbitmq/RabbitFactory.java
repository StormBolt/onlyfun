package com.onlyfun.learn.rabbitmq.hellorabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

/**
 * Created by jareddu on 16-5-27.
 */
public class RabbitFactory {
    Logger logger = Logger.getLogger(RabbitFactory.class);
    private Channel channel;
    private Connection connection;
    private String topic;

    public RabbitFactory(String topic) {
        try {
            this.topic = topic;
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("10.5.28.81");
            factory.setPort(5672);
            factory.setUsername("dujiacheng");
            factory.setPassword("dujiacheng");
            connection = factory.newConnection();
            channel = connection.createChannel();
            /**
             * queue - the name of the queuedurable
             * true - if we are declaring a durable queue (the queue will survive a server restart)exclusive
             * true - if we are declaring an exclusive queue (restricted to this connection)autoDelete
             * true - if we are declaring an autodelete queue (server will delete it when no longer in use)arguments
             * other - properties (construction arguments) for the queue
             */
            channel.queueDeclare(topic, true, false, false, null);
        }catch (Exception e){
            logger.error("init rabbit factory fail ...");
            e.printStackTrace();
        }
    }

    /**
     * close channel,connection
     * @throws Exception
     */
    public void close() throws Exception{
        if (this.channel == null || this.connection == null){
            logger.error("channel is " + channel + " |  connection is " + connection);
            return;
        }

        this.channel.close();
        this.connection.close();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

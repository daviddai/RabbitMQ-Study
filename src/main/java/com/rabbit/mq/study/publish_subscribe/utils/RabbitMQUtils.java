package com.rabbit.mq.study.publish_subscribe.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        return connectionFactory.newConnection();
    }


    public static Channel createChannel(Connection connection, String exchangeName, String exchangeType) {
        Channel channel = null;

        try {
            channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, exchangeType);
        } catch (IOException e) {
            tryCloseChannel(channel);
            return null;
        }

        return channel;
    }

    private static void tryCloseChannel(Channel channel) {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (TimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void tryCloseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}

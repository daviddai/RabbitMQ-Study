package com.rabbit.mq.study.publish_subscribe.publisher;

import com.rabbit.mq.study.publish_subscribe.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogEmitter {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = RabbitMQUtils.createChannel(connection, "logs", "fanout");
        if (channel != null) {
            channel.basicPublish("logs", "", null, "Hello World".getBytes());
            System.out.println("Log is sent");
        }
    }
}

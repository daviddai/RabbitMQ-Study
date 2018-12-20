package com.rabbit.mq.study.publish_subscribe.subscriber;

import com.rabbit.mq.study.publish_subscribe.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogReceiver {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = RabbitMQUtils.createChannel(connection, "logs", "fanout");
        if (channel != null) {
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, "logs", "");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
    }

}

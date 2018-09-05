package com.yu.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created By Yu On 2018/9/5
 * Description：
 **/
public class NewTask {
    private final static String QUEUE_NAME = "hello223";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        String message = getMessage(args);
        channel.basicPublish("", QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes("UTF-8"));
        System.out.println(" [x] Send '" + message + "'");
    }

    // 获取咨询的方法，返回引数，如果没有则返回hello world
    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "Hello World!";
        }
        return joinString(strings, " ");
    }


    private static String joinString(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder stringBuilder = new StringBuilder(strings[0]);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(delimiter).append(strings[i]);
        }
        return stringBuilder.toString();
    }
}

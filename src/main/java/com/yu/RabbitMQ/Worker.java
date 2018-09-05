package com.yu.RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created By Yu On 2018/9/5
 * Description：
 **/
public class Worker {
    private final static String QUEUE_NAME = "hello223";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                }
            }
        };
        // false 开启manaul message ack
        // true 代表只有发出消息都会自动有一个ack
        // false 代表服务器会等待明确的ack，不会自动返回
        boolean autoAck = true; //  acknowledgment is covered below

        String result = channel.basicConsume(QUEUE_NAME, autoAck, consumer);
        System.out.println("result:" + result);
    }

    // 模拟执行任务的方法，一个点代表一秒
    private static void doWork(String task) throws InterruptedException {
        for (char c : task.toCharArray()) {
            if (c == ',') Thread.sleep(1000);
        }

    }
}

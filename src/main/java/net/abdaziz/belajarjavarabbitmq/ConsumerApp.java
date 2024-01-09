package net.abdaziz.belajarjavarabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumerApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:devP@ssw0rd@localhost:5672/");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (tag, message) -> {
            System.out.println(message.getEnvelope().getRoutingKey());
            System.out.println(new String(message.getBody()));

        };

        CancelCallback cancelCallback = (tag) -> {
            System.out.println("Consumer is canceled");
        };

        channel.basicConsume("whatsapp", true, deliverCallback, cancelCallback);

        // channel.close();
        // connection.close();
    }
}

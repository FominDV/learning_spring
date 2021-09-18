package ru.fomin;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqUtil {

    public static final String EXCHANGE_NAME = "ArticleDirect2";

    public static ConnectionFactory getFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        return factory;
    }

    public static Channel getChannel() throws IOException, TimeoutException {
        Connection connection = getFactory().newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        return channel;
    }

    public static String getQueue(Channel channel) throws IOException {
        return channel.queueDeclare().getQueue();
    }

}

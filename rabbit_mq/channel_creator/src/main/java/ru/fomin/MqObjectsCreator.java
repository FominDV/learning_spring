package ru.fomin;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqObjectsCreator {

    public ConnectionFactory getFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPassword("admin");
        factory.setUsername("admin");
        return factory;
    }

}

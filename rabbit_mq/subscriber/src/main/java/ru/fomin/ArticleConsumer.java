package ru.fomin;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ArticleConsumer {

    private static final String COMMAND_SET = "set_topic";
    private static final String COMMAND_REMOVE = "del_topic";
    private static final String COMMAND_PATTERN = "^(" + COMMAND_SET + "|" + COMMAND_REMOVE + ") .+$";
    private static final String MESSAGE_INFO_MANAGE_SUBSCRIPTION = String.format("For adding subscription input \"%s\", one space and topic name.\nFor removing subscription input \"%s\", one space and topic name.", COMMAND_SET, COMMAND_REMOVE);
    private static final String MESSAGE_INFO_BAD_COMMAND = "Invalid command was input";
    private static final String MESSAGE_INFO_NEW_ARTICLE = "New article: %s\n";

    private DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        System.out.printf(MESSAGE_INFO_NEW_ARTICLE, message);
    };

    public void execute() throws IOException, TimeoutException {
        Channel channel = MqUtil.getChannel();
        String queueName = MqUtil.getQueue(channel);
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        readCommands(channel, queueName);
    }

    private void readCommands(Channel channel, String queueName) throws IOException, TimeoutException {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(MESSAGE_INFO_MANAGE_SUBSCRIPTION);
                processCommand(scanner.nextLine(), channel, queueName);
            }
        }
    }

    private void processCommand(String command, Channel channel, String queueName) throws IOException, TimeoutException {
        if (!command.matches(COMMAND_PATTERN)) {
            System.out.println(MESSAGE_INFO_BAD_COMMAND);
            return;
        }
        int indexFirstSpace = command.indexOf(" ");
        String topic = command.substring(indexFirstSpace);
        switch (command.substring(0, indexFirstSpace)) {
            case COMMAND_SET:
                channel.queueBind(queueName, MqUtil.EXCHANGE_NAME, topic);
                break;
            case COMMAND_REMOVE:
                channel.queueUnbind(queueName, MqUtil.EXCHANGE_NAME, topic);
                break;
            default:
                System.out.println(MESSAGE_INFO_BAD_COMMAND);
        }
    }

}

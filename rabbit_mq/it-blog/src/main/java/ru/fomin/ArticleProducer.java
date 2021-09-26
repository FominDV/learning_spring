package ru.fomin;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ArticleProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleProducer.class);
    private static final String MESSAGE_PATTERN = "^\\S{2,} .+";
    private static final String MESSAGE_INFO_EXIT = "For exit input \"stop\"";
    private static final String MESSAGE_INFO_CREATE_ARTICLE = "For creating new article you should input topic, one space and article text.";
    private static final String MESSAGE_INFO_BAD_MESSAGE = "Invalid message was input.";
    private static final String MESSAGE_INFO_ARTICLE_WAS_SENT = "The article which has topic \"%s\" and message \"%s\" was sent successfully.";

    public void execute() {
        readInputMessages(MqUtil.getFactory());
    }

    private void readInputMessages(ConnectionFactory factory) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
             Scanner scanner = new Scanner(System.in)) {
            channel.exchangeDeclare(MqUtil.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            LOGGER.info(MESSAGE_INFO_CREATE_ARTICLE);
            while (true) {
                LOGGER.info(MESSAGE_INFO_EXIT);
                String message = scanner.nextLine();
                if (message.equals("stop")) {
                    break;
                }
                readArticleMessage(message, channel);
            }
        } catch (IOException e) {
            LOGGER.error("error", e);
        } catch (TimeoutException e) {
            LOGGER.error("error", e);
        }
    }

    private void readArticleMessage(String inputMessage, Channel channel) {
        if (!inputMessage.matches(MESSAGE_PATTERN)) {
            LOGGER.info(MESSAGE_INFO_BAD_MESSAGE);
            return;
        }
        int indexFirstSpace = inputMessage.indexOf(" ");
        String topic = inputMessage.substring(0, indexFirstSpace);
        String message = inputMessage.substring(indexFirstSpace + 1);
        publish(topic, message, channel);
    }

    private void publish(String topic, String message, Channel channel) {
        try {
            channel.basicPublish(MqUtil.EXCHANGE_NAME, topic, null, message.getBytes("UTF-8"));
            LOGGER.info(String.format(MESSAGE_INFO_ARTICLE_WAS_SENT, topic, message));
        } catch (IOException e) {
            LOGGER.error("error", e);
        }
    }

}

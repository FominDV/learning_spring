package ru.fomin;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerLauncher {

    public static void main(String[] args) throws IOException, TimeoutException {
        new ArticleConsumer().execute();
    }

}

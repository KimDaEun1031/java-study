package org.daeun.kafka.producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class SenderApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        String topic = getTopic();

        send(topic);
    }

    private static String getTopic() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("topic : ");
        String topic = bufferedReader.readLine();

        bufferedReader.close();
        return topic;
    }

    private static void send(String topic) throws InterruptedException {
        Sender sender = new Sender();

        for (int i = 0; i < 10; i++) {
            sender.sendMessage(topic,"test " +i, LocalDateTime.now().toString());
            TimeUnit.MILLISECONDS.sleep(1000);
        }

        sender.close();
    }
}

package org.daeun.kafka.consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReceiverApplication {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("group : ");
        String group = bufferedReader.readLine();

        System.out.print("topic : ");
        String topic = bufferedReader.readLine();

        System.out.print("name : ");
        String name = bufferedReader.readLine();

        bufferedReader.close();

        receive(group, topic, name);
    }

    private static void receive(String group, String topic, String name) {
        Receiver receiver = new Receiver(name, group, topic);

        System.out.println("polling..");
        while (true) {
            receiver.poll();
        }
    }


}

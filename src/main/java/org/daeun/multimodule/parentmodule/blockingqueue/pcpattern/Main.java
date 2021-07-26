package org.daeun.multimodule.parentmodule.blockingqueue.pcpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(30);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        SecondConsumer secondConsumer = new SecondConsumer(queue);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        Thread threadSecondConsumer = new Thread(secondConsumer);

        threadProducer.start();
        threadConsumer.start();
        threadSecondConsumer.start();

        System.out.println("Test Start");

    }
}

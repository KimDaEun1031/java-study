package org.daeun.blockingqueue.pcpatternthreadtest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(1000000);
        Producer producer = new Producer(queue);
        FirstConsumer consumer = new FirstConsumer(queue);
//        SecondConsumer secondConsumer = new SecondConsumer(queue);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
//        Thread threadSecondConsumer = new Thread(secondConsumer);

        threadProducer.setName("sendData");
        threadConsumer.setName("receiveData1");
//        threadSecondConsumer.setName("receiveData2");

        threadProducer.start();
        threadConsumer.start();
//        threadSecondConsumer.start();

        System.out.println("PC Test Start");

    }
}

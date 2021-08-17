package org.daeun.linkedlistqueueperformancetest.pcpattern;

import lombok.extern.slf4j.Slf4j;
import org.daeun.linkedlistqueueperformancetest.message.Message;
import org.daeun.linkedlistqueueperformancetest.pcpattern.producer.*;

import java.util.LinkedList;

@Slf4j
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LinkedList<Message> queue = new LinkedList<>();
        Producer producer = new Producer(queue);
        for(int i=0; i<10000; i++) {
            Thread thread = new Thread(producer);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        log.info("최종완료 시간 : "+(end-start)/1000.0+"초");
        log.info(String.valueOf(queue.size()));

        Consumer consumer = new Consumer(queue);

         Thread threadConsumer = new Thread(consumer);

        threadConsumer.start();


    }

}

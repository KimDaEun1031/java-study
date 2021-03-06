package org.daeun.linkedlistqueueperformancetest.pcpattern;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.daeun.linkedlistqueueperformancetest.message.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {

        //프로듀서가 생성한 메세지를 넣고 콘슈머가 메세지를 뺄 수 있게 메세지를 저장하는 공간
        Queue<Message> queue = new LinkedList<>();
        //데이터를 생성하는 클래스를 생성
        Producer producer = new Producer(queue);
        //데이터를 소비하는 클래스를 생성
        Consumer consumer = new Consumer(queue);


        //Thread Producer 배열 생성
        Thread[] threadProducerArray = new Thread[11];
        for (int index = 0; index < threadProducerArray.length; index++) {
            threadProducerArray[index] = new Thread(producer);
            threadProducerArray[index].setName("sendData" + (index + 1));
            threadProducerArray[index].start();
        }


        //Thread Consumer 배열 생성
        Thread[] threadConsumerArray = new Thread[100];
        for (int index = 0; index < threadConsumerArray.length; index++) {
            threadConsumerArray[index] = new Thread(consumer);
            threadConsumerArray[index].setName("receiveConsumerData"+(index+1));
            threadConsumerArray[index].start();
        }

        log.info("Test Start");
    }

}




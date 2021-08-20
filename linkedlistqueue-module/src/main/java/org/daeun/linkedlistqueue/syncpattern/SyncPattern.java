package org.daeun.linkedlistqueue.syncpattern;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class SyncPattern {
    @SneakyThrows
    public static void main(String[] args) {
        //프로듀서가 생성한 메세지를 넣고 콘슈머가 메세지를 뺄 수 있게 메세지를 저장하는 공간
        Queue<Message> queue = new LinkedList<>();
        //데이터를 생성하는 클래스를 생성
        Producer producer = new Producer(queue);
        //데이터를 소비하는 클래스를 생성
        Consumer consumer = new Consumer(queue);

        //프로듀서 스레드 생성
        Thread threadProducer = new Thread(producer);
        //프로듀서 스레드 이름 지정
        threadProducer.setName("sendData");


        //스레드 콘슈머 배열
        Thread[] threadConsumerArray = new Thread[1];
        for (int index = 0; index < threadConsumerArray.length; index++) {
            //스레드 콘슈머 생성
            threadConsumerArray[index] = new Thread(consumer);
            threadConsumerArray[index].setName("receiveConsumerData"+(index+1));
            threadConsumerArray[index].start();
        }

        //프로듀서 스레드 시작
        threadProducer.start();


        System.out.println("Test Start");
    }
}

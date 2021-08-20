package org.daeun.linkedlistqueueperformancetest.pcpattern;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.daeun.linkedlistqueueperformancetest.message.Message;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j

public class Producer implements Runnable {

    private Queue<Message> queue;
    private AtomicInteger counter = new AtomicInteger();

    public Producer(Queue<Message> q) {
        this.queue = q;
    }

    @SneakyThrows
    @Override
    public void run() {
        long start = System.nanoTime();

        int localCount = 0;
        while (true) {
            int index = counter.incrementAndGet();
            Message msg = new Message("" + index);

            //해당 객체(queue)에 Lock 걸어 여러 개의 thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                //0.001초 대기
                queue.add(msg);
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            }

            //localCount를 100으로 나누었을 때 0으로 떨어지면 0.001초 대기한다
            if(++localCount % 100 == 0) {
                Thread.sleep(1);
                log.info("localCount = {}, count ={}", localCount, counter.get());
            }

            //counter의 값이 100만보다 크거나 같다면 종료
            if (counter.get() >= 1000000)
                break;
        }

        log.info("Producer total time = {}", System.nanoTime() - start);
        log.info("Result : count = {}", counter.get());
        log.info("queue size = {}", queue.size());

        //exit 메세지를 생성한다
        Message msg = new Message("exit");

        synchronized (queue) {
            for (int i = 0; i < 10; i++) {
                queue.add(msg);
            }
        }
    }


}

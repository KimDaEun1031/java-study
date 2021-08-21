package org.daeun.linkedlistqueueperformancetest.pcpattern;

import lombok.extern.slf4j.Slf4j;
import org.daeun.linkedlistqueueperformancetest.message.Message;

import java.util.Objects;
import java.util.Queue;

@Slf4j
public class Consumer implements Runnable {

    private Queue<Message> queue;

    public Consumer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        int localCount = 0;
        long start = System.nanoTime();

        try {
            Message msg;


            while (true) {
                //해당 객체(queue)에 Lock 걸어 여러 개의 thread가 동시에 접근하는 것을 막는다
                synchronized (queue) {
                    //queue 안에 값이 있다면 값을 반환한 후에 제거하고 비어있다면 Null 값을 반환한다
                    msg = queue.poll();
//                    log.info("msg = {}", msg);
                }
//                log.info("msg = {}", msg);
                //msg가 null이 아닐 경우 if문 바로 다음 문장들 수행
                if (Objects.nonNull(msg)) {
                    //localCount를 10으로 나누었을 때 0으로 떨어지면 0.001초로 대기한다
                    if (++localCount % 10 == 0) {
                        Thread.sleep(1);
                        log.info("localCount = {}, queue size = {}", localCount, queue.size());
                    }

                    //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
                    //큐에서 뽑은 값이 exit와 같다면 종료한다
                    if (msg.getMsg().equals("exit")) {
                        break;
                    }

                }



            }

            log.info("Consumer total time = {}", System.nanoTime() - start);
            log.info("queue size = {}", queue.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

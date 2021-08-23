package org.daeun.linkedlistqueue.pcpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;

@Slf4j
//데이터를 소비하는 클래스
public class Consumer implements Runnable {
    //프로듀서가 생성한 메세지가 들어있는 공간
    private final Queue<Message> queue;

    //메인에서 인스턴스 변수 queue를 사용할 수 있게 하는 메소드
    public Consumer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    //스레드에서 실행되는 메소드
    public void run() {
        try {
            Message msg;

            long start = System.nanoTime();
            //해당 객체(queue)에 Lock 걸어 두개의 thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                //무한루프
                while (true) {
                    //데이터가 들어있지 않고 비었다면 데이터가 올 때 까지 대기
                    if (queue.isEmpty()) {
                        queue.wait();
                    }

                    //queue 안에 값이 있다면 값을 반환한 후에 제거하고 비어있다면 Null 값을 반환한다
                    msg = queue.poll();
//                    log.info("msg = {}", msg);

                    //msg가 null이 아닐 경우 if문 바로 다음 문장들 수행
                    if (Objects.nonNull(msg)) {
                        //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
                        //큐에서 뽑은 값이 exit와 같다면 종료한다
                        if (msg.getMsg().equals("exit")) {
                            break;
                        }
//                        log.info("msg is not null, msg = {}", msg.getMsg());
                    }

//                    log.info("queue size = {}", queue.size());
                }
            }
            log.info("total time = {}", System.nanoTime() - start);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



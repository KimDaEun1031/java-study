package org.daeun.blockingqueue.pcpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

@Slf4j
//데이터를 소비하는 클래스
public class Consumer implements Runnable {
    //프로듀서가 생성한 메세지가 들어있는 공간
    private BlockingQueue<Message> queue;
    //메인에서 인스턴스 변수 queue를 사용할 수 있게 하는 메소드
    public Consumer(BlockingQueue<Message> q) {
        this.queue = q;
    }

    @Override
    //스레드에서 실행되는 메소드
    public void run() {
        try {
            Message msg;

            //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
            //큐에서 뽑은 값이 exit와 같다면 종료한다
            while (!(msg = queue.take()).getMsg().equals("exit")) {
                Thread.sleep(1000);
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

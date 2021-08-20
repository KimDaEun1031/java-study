package org.daeun.blockingqueue.pcpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

@Slf4j
//데이터를 생성하는 클래스
public class Producer implements Runnable {
    //프로듀서가 데이터를 넣어야하는 공간
    private BlockingQueue<Message> queue; //인스턴스 변수
    //메인에서 인스턴스 변수 queue를 사용할 수 있게 하는 메소드
    public Producer(BlockingQueue<Message> q) { this.queue = q; } //() 매개변수

    @Override
    //스레드에서 실행되는 메소드
    public synchronized void run() {
        for (int i = 0; i <= 10; i++) {
            Message msg = new Message("" + i);
            try {
                Thread.sleep(500);
                //큐 안에 데이터를 삽입한다.
                queue.put(msg);
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //exit 메세지를 생성한다
        Message msg = new Message("exit");
        for (int i = 0; i < 3; i++) {
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

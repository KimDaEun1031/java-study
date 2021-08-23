package org.daeun.linkedlistqueue.syncpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
//데이터를 생성하는 클래스
public class Producer implements Runnable {
    //프로듀서가 데이터를 넣어야하는 공간
    private final Queue<Message> queue; //인스턴스 변수

    //메인에서 인스턴스 변수 queue를 사용할 수 있게 하는 메소드
    public Producer(Queue<Message> q) {
        this.queue = q;
    } //() 매개변수

    @Override
    //스레드에서 실행되는 메소드
    public void run() {
        for (int i = 0; i <= 10000000; i++) {
            Message msg = new Message("" + i);

            //해당 객체(queue)에 Lock 걸어 두개의 thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                queue.add(msg);
//                log.info("msg = {}", msg);
//                log.info("queue size = {}", queue.size());
            }
        }

        //exit 메세지를 생성한다
        Message msg = new Message("exit");

        //해당 객체(queue)에 Lock 걸어 두개의 thread가 동시에 접근하는 것을 막는다
        synchronized (queue) {
            for (int i = 0; i < 20; i++) {
                queue.add(msg);
            }
        }

    }
}

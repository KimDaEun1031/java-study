package org.daeun.blockingqueue.pcpatternthreadtest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Producer implements Runnable {
    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for(int i=0;i<=1000000;i++) {
            Message msg = new Message(""+i);
            try {
//                Thread.sleep(100);
                queue.put(msg);
//                System.out.println(Thread.currentThread().getName() + " : " + msg.getMsg());
//                log.info("id = {}",Thread.currentThread().getId());
//                log.info("size = {}", queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("시간 : "+(end-start)/1000.0+"초");

        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

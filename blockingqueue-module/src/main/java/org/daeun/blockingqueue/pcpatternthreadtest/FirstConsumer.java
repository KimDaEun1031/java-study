package org.daeun.blockingqueue.pcpatternthreadtest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class FirstConsumer implements Runnable {
    private BlockingQueue<Message> queue;

    public FirstConsumer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            Message msg;
            long start = System.currentTimeMillis();
            while ((msg = queue.take()).getMsg() !="exit") {
//                Thread.sleep(1000);
//                System.out.println(Thread.currentThread().getName() + " : " + msg.getMsg());
//                log.info("id = {} ",Thread.currentThread().getId());
//                log.info("size = {}", queue.size());
            }
            long end = System.currentTimeMillis();
            System.out.println("시간 : "+(end-start)/1000.0+"초");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

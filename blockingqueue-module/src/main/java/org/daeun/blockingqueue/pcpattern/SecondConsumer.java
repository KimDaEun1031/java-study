package org.daeun.blockingqueue.pcpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class SecondConsumer implements Runnable {
    private BlockingQueue<Message> queue;

    public SecondConsumer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            Message msg;

            while ((msg = queue.take()).getMsg() !="exit") {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " : " + msg.getMsg());
                log.info(Thread.currentThread().getName()+ " id = {} ", Thread.currentThread().getId());
                log.info(Thread.currentThread().getName()+ " size = {}", queue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

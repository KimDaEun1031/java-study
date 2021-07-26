package org.daeun.multimodule.parentmodule.blockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Consumer implements Runnable {
    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            Message msg;

            while ((msg = queue.take()).getMsg() !="exit") {
                Thread.sleep(10);
                System.out.println("Consumed"+msg.getMsg());
                log.info("Consumer thread id : "+Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

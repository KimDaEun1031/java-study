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
                Thread.sleep(10);
                System.out.println("SecondConsumed"+msg.getMsg());
                log.info("SecondConsumer thread id : "+Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

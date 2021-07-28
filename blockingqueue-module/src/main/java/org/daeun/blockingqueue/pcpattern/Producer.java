package org.daeun.blockingqueue.pcpattern;

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
        for(int i=0;i<=10;i++) {
            Message msg = new Message(""+i);
            try {
                Thread.sleep(2000);
                queue.put(msg);
                System.out.println(Thread.currentThread().getName() + " : " + msg.getMsg());
                log.info(Thread.currentThread().getName()+ " id = {}",Thread.currentThread().getId());
                log.info(Thread.currentThread().getName() + " size = {}", queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

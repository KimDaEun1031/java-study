package org.daeun.linkedlistqueue.pcpattern;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FirstConsumer implements Runnable{
    private static List<String> queue;

    public FirstConsumer(List<String> q) {
        queue=q;
    }

    @Override
    public void run() {
        try {
            boolean isExit = queue.contains("exit");
            Message msg = null;
            while (msg.getMsg() != "exit") {
                Thread.sleep(10);
                System.out.println("Consumed"+msg.getMsg());
                log.info("Consumer thread id : "+Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


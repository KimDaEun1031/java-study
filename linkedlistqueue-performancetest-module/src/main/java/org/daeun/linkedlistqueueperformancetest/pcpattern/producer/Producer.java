package org.daeun.linkedlistqueueperformancetest.pcpattern.producer;

import lombok.extern.slf4j.Slf4j;
import org.daeun.linkedlistqueueperformancetest.message.Message;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class Producer implements Runnable {
    private Queue<Message> queue;

    public Producer(LinkedList<Message> q) {this.queue=q;}

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        synchronized (this) {
            for(int i=0;i<=100;i++) {
                Message msg = new Message("" + i);
                try {
//                    Thread.sleep(1);
                    queue.offer(msg);
//                    log.info(msg.getMsg());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        log.info("시간 : "+(end-start)/1000.0+"초");

        Message msg = new Message("exit");
        try {
            queue.offer(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

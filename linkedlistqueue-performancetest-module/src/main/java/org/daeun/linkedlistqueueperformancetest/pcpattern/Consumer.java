package org.daeun.linkedlistqueueperformancetest.pcpattern;

import org.daeun.linkedlistqueueperformancetest.message.Message;

import java.util.LinkedList;
import java.util.Queue;

public class Consumer implements Runnable{
    private Queue<Message> queue = new LinkedList<>();

    public Consumer(LinkedList<Message> q) {this.queue=q;}

    @Override
    public void run() {
        try {
            Message msg;
            long start = System.currentTimeMillis();
            synchronized (this) {
//                while ((msg = queue.poll()).getMsg() !="exit") {
//                    System.out.println(msg.getMsg());
//                }
            }
            long end = System.currentTimeMillis();
//            System.out.println("시간 : "+(end-start)/1000.0+"초");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

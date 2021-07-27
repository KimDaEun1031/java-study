package org.daeun.linkedlistqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class LinkedListQueueMultiThreadPractice {

    public static void main(String[] args) throws Exception {
        List q = Collections.synchronizedList(new LinkedList<String>());
        Thread p1 = new Thread(new PrepareProduction(q));
        Thread c1 = new Thread(new DoProduction(q));
        p1.start();
        c1.start();
        p1.join();
        c1.join();


    }
}

class PrepareProduction implements Runnable {
    private final List<String> queue;

    PrepareProduction(List<String> q) {
        queue = q;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            queue.add("LinkedListQueue Test Number : "+ i);
        }
    }
}

class DoProduction implements Runnable {
    private final List<String> queue;

    DoProduction(List<String> q) {
        queue = q;
    }

    @Override
    public void run() {
        System.out.println("출력");
        for (int i=0; i<queue.size(); i++) {
            System.out.println(queue.get(i));
        }

    }
}
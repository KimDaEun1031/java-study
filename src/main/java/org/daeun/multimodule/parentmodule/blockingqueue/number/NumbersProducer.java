package org.daeun.multimodule.parentmodule.blockingqueue.number;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class NumbersProducer implements Runnable{

    private BlockingQueue<Integer> numbersQueue;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    public NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) {
        this.numbersQueue = numbersQueue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        for (int i=0; i< 10; i++) {
            numbersQueue.put(ThreadLocalRandom.current().nextInt(10));
            //queue에 랜덤 숫자 100개를 넣는다.
        }
        for (int j=0; j< poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
        }
    }
}

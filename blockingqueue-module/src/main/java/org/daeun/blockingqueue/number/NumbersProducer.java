package org.daeun.blockingqueue.number;



import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
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
            Thread.sleep(500);
            numbersQueue.put(i);
            log.info("number queue = {}", numbersQueue.size());
        }
        for (int j=0; j< poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
        }
    }
}

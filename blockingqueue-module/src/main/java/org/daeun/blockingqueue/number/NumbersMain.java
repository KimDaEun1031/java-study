package org.daeun.blockingqueue.number;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class NumbersMain {

    public static void main(String args[]) {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        log.info("N_Consumer = {}", N_CONSUMERS);
        int poisonPill = Integer.MAX_VALUE;
        log.info("poisonPill = {}", poisonPill);
        int poisonPillPerProducer = N_CONSUMERS/N_PRODUCERS;
        log.info("poisonPillPerProducer = {}", poisonPillPerProducer);
        int mod = N_CONSUMERS % N_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(BOUND);

        for(int i=1; i<N_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }
        for(int i=1; i<N_CONSUMERS; i++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }

        new Thread(new NumbersProducer(queue,poisonPill,poisonPillPerProducer+mod)).start();
    }
}

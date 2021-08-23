package org.daeun.multithread.thread;

public class ThreadPerformanceTest {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            MyThread.sum = 0;
            int threadCount = 4;
            int target = 10000000;
            MyThread[] threads = new MyThread[threadCount];
            for(int i=0; i< threads.length; i++) {
                threads[i] = new MyThread(i * (target / threadCount), (i+1)*(target/threadCount)-1);
                threads[i].start();
            }
            for(int i=0; i< threads.length; i++) {
                threads[i].join();
            }
            long end = System.currentTimeMillis();
            System.out.println("합계 : " +MyThread.sum);
            System.out.println("연산시간 : " +(end-start)/1000.0+"초");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    public static long sum;
    private final int start;
    private final int end;

    public MyThread(int start, int end) {
        this.start=start;
        this.end=end;
    }

    @Override
    public void run() {
        long temp = 0;
        for(int i=start; i<=end; i++) {
            try {
                Thread.sleep(1);
                temp += i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sum +=temp;
    }
}
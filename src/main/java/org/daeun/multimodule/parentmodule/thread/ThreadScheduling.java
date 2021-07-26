package org.daeun.multimodule.parentmodule.thread;

public class ThreadScheduling {
    public static void main(String[] args) {
        for(int i=0;i<=5;i++) {
            Thread thread = new Scheduled("[ Thread " + i + " ]");
            if (i == 5)
                thread.setPriority(Thread.MAX_PRIORITY);
            else
                thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }
    }

}

class Scheduled extends Thread {
    public Scheduled(String threadName) {
        this.setName(threadName);
    }

    public void run() {
        for(int i=0; i<1000; i++) { //시간을 지연시키기 위함
            int x = 100; x += i;
            for(int j = 0; j< 10000; j++) {
                x += j;
            }
        }

        System.out.println(this.getName() + " Thread_Start ");
    }
}

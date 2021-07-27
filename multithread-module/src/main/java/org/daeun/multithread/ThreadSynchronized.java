package org.daeun.multithread;

public class ThreadSynchronized {

    public static void main (String[] args) {
        // java.lang.thread 클래스를 직접 객체화 해서 생성하는 방법

        Task task = new Task();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.setName("t1-thread");
        t2.setName("t2-thread");

        t1.start();
        t2.start();
    }
}

class Account{
    int balance = 1000;

    public synchronized void withDraw(int money) {
        if(balance >= money) {
            try {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + " 출금 금액 ==> " +money);
                Thread.sleep(1000);
                balance -= money;
                System.out.println(thread.getName() + " balance: " +balance);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Task implements Runnable {
    Account acc = new Account();

    @Override
    public void run() {
        while(acc.balance > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withDraw(money);
        }
    }
}
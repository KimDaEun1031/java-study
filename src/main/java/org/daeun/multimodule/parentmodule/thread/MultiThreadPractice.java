package org.daeun.multimodule.parentmodule.thread;

public class MultiThreadPractice {
    public static void main(String[] args) throws Exception {
        // Thread를 상속해서 하위클래스를 만들어 생성하는 방법

        // 하나의 Thread 생성
        MultiThread[] multiThread = new MultiThread[1];
        multiThread[0] = new MultiThread();
        multiThread[0].start();

        // 다수의 Thread 생성
        MultiThread[] multiThreads = new MultiThread[3];
        for(int i=0; i<multiThreads.length; i++) {
            multiThreads[i] = new MultiThread();
            multiThreads[i].start();
        }

        // 다수의 Thread 시 공유되는 객체 문제점 해결
        System.out.println("This is One Thread Program");
        MultiThreadProblem[] multiThreadProblem = new MultiThreadProblem[3];
        for (int i=0; i<multiThreadProblem.length;i++) {
            multiThreadProblem[i] = new MultiThreadProblem();
            multiThreadProblem[i].start();
        }


    }
}

class MultiThread extends Thread {
    MultiThread(){}
    public void run(){
        try {
            System.out.println("This is One Thread Program");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MultiThreadProblem extends Thread {
    SolveProblem sp = new SolveProblem();
    MultiThreadProblem(){}
    public void run() {
        try {
            String lang = "eng";
            sp.story(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SolveProblem {
    public synchronized void story(String lang) {
        lang = "kor";
        System.out.println(String.format("This is Thread Program Error money = %s",lang));
    }
}
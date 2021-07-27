package org.daeun.blockingqueue.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {

    public static void main(String[] args) {
        Queue<String> que = new LinkedList<String>();

        que.offer("김다은");
        que.offer("김유키");
        que.offer("김이언");

        System.out.println("Queue 안에 값 포함 여부 : " + que.contains("김이언"));
        System.out.println("Queue 다음 출력값 확인 : " + que.peek());
        System.out.println("Queue 크기 확인 : " + que.size());

        que.remove("김유키");

        for (int i=0; i<que.size();) {
            System.out.println(que.poll());
        }

        que.clear();
        System.out.println("Queue 비었는지 확인 : " + que.isEmpty());
    }
}

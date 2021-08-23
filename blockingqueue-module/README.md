# Blocking Queue Module

#### Blocking Queue
java.util.concurrent 패키지에 있는 인터페이스로 Thread-safe한 Queue이다. 특정 상황에 스레드를 대기하도록 하는 Queue이며 Queue가 꽉 차있는데 Put을 하거나(enqueue()) 비어있는데 Take를(dequeue()) 할 때 enqueue/dequeue 호출 스레드를 대기하도록 한다. enqueue/dequeue 상태는 Queue에 Put/Take가 될 때 까지 지속된다.

#### Blocking Queue 구현체
|구현체 이름|설명|
|----|----|
|ArrayBlockingQueue|고정 배열에 일반적인 Queue를 구현한 클래스|
|LinkedBlockingQueue|선택적으로 Bound가 가능한 Linked list로 구현한 Queue|
|PriorityBlockingQueue|정렬 방식을 지니는 용량제한이 없는 Queue|
|SynchronousQueue|버퍼가 없어 대기 Thread list를 관리하는 Queue|

#### Blocking Queue 특징
1. null을 허용하지 않는다. 
   + null 값을 add/put/offer를 하면 NullPointerException 발생
   + null 값은 poll 작업의 실패를 나타낼 때 사용한다.

2. capacity bound
   + Blocking Queue는 용량, capacity에 제한을 둘 수 있다.
   + capacity에 제약을 두지 않으면 Integer.Max_Value이 기본으로 설정된다.

3. Collection 인터페이스 지원
   + BlockingQueue는 Producer-Consumer 패턴의 대기열 목적으로 디자인되었다.
     하지만 Collection 인터페이스를 지원해서, remove(x)와 같이 임의의 데이터를 Queue에서 지울 수 있다.
     이 작업은 매우 비효율적이며, 대기중인 메세지가 취소되는 경우와 같이 가끔씩 사용하기 위해 만든다.

4. Thread-safe가 되게 구현
   + 모든 Queue 메소드들은 내부적으로 locks 또는 동시성 제어를 사용하여 원자성을 보장한다.
   + Bulk Collection Operations (addAll, containsAll, retainAll, removeAll 등)은 원자성을 보장하지 않는다.(원자성을 보장하려면 따로 구현)
   + addAll(c)로 100개의 데이터를 queue에 넣을 때, 일부는 넣고 실패할 수 있다. (throwing Exception)
 
5. 더 이상 데이터가 추가되지 않는 것을 나타내기 위한 close,shutdown같은 작업을 지원하지 않는다
   + 목적에 맞춰서 구현해야한다.
   + 일반적으로 producer가 특수한 end-of-stream 또는 poison 객체를 삽입하고 , consumer가 판단하는 패턴으로 구현해야한다.\
   
#### Blocking Queue Method
![61859757-b486b300-af03-11e9-9ad7-57f00107d003](https://user-images.githubusercontent.com/73865700/126966058-0b8741b8-ecef-4170-98f3-e7183b2e1b72.png)

#### Multi Thread Design Pattern - Producer&Consumer Pattern
생산자/소비자 패턴은 멀티스레드 환경에서 주로 쓰이는 패턴이다. 생산자와 소비자는 하나의 클래스를 공유하고 그 클래스는 Queue 구조를 가지게 된다. 생산자는 데이터를 지속적으로 생산해 Queue에 Push하는 일을 소비자는 데이터를 지속적으로 소비해 Queue에서 Pop하는 일을 한다.

#### Producer&Consumer Pattern 예제 & 구조

1. Blocking Queue를 이용한 Producer&Consumer Pattern (1)
+ 참고 사이트 : https://niceman.tistory.com/94

![dddfdfdfdf](https://user-images.githubusercontent.com/73865700/126962213-ce65c4a1-79ee-4f6a-b98f-19f967976ae1.png)

**변경**
- Consumer Class 2개 -> 1개
- Producer Class Thread.sleep(i) -> Thread.sleep(1000)
- Main Class Thread array로 Consumer 생성


#### Message Class
```
public class Message {
    private String msg;

    public Message(String str) {
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }
}
```

#### Consumer Class
```
@Slf4j
//데이터를 소비하는 클래스
public class Consumer implements Runnable {
    //Producer가 생성한 메세지가 들어있는 공간
    private BlockingQueue<Message> queue;
    
    //Main에서 인스턴스 변수 queue를 사용할 수 있게 하는 메소드
    public Consumer(BlockingQueue<Message> q) {
        this.queue = q;
    }

    @Override
    //Thread에서 실행되는 메소드
    public void run() {
        try {
            Message msg;

            //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
            //큐에서 뽑은 값이 exit와 같다면 종료한다
            while (!(msg = queue.take()).getMsg().equals("exit")) {
            	//0.5초 대기
                Thread.sleep(500);
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### Producer Class
```
@Slf4j
//데이터를 생성하는 클래스
public class Producer implements Runnable {

    //Producer가 데이터를 입력하는 공간
    private BlockingQueue<Message> queue; //인스턴스 변수
    
    //Main에서 인스턴스 변수 queue를 사용할 수 있게 하는 메소드
    public Producer(BlockingQueue<Message> q) {  //() 매개변수
    	this.queue = q; 
    } 

    @Override
    //Thread에서 실행되는 메소드
    public synchronized void run() {
        for (int i = 0; i <= 10; i++) {
            Message msg = new Message("" + i);
            try {
            	//1초 대기
                Thread.sleep(1000);
                //Queue에 msg 삽입
                queue.put(msg);
				
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //종료 용 exit 메세지를 생성한다
        Message msg = new Message("exit");
        
        //끝내야하는 Thread 개수보다 많이 넣는다
        for (int i = 0; i < 3; i++) {
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### Main Class
```
@Slf4j
public class Main {
    public static void main(String[] args) {
    
        //Producer와 Consumer가 공유하는 자원
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(5); //5개로 용량을 제한
        
        //데이터를 생성하는 클래스를 생성
        Producer producer = new Producer(queue);
        //데이터를 소비하는 클래스를 생성
        Consumer consumer = new Consumer(queue);

        //Producer와 스레드 생성
        Thread threadProducer = new Thread(producer);
        //Producer와 스레드 이름 지정
        threadProducer.setName("sendData");
        //Producer와 스레드 시작
        threadProducer.start();

        Thread[] threadConsumerArray = new Thread[2]; //2개 생성
        for (int index = 0; index < threadConsumerArray.length; index++) {
            //Thread Consumer 생성
            threadConsumerArray[index] = new Thread(consumer);
            threadConsumerArray[index].setName("receiveConsumerData"+(index+1)); 
            threadConsumerArray[index].start();
        }

        log.info("Test Start");
    }
}
```
-------
#### Thread Latency Time Test
1. Capacity는 10으로 하였고 Producer 클래스에서 Thread.sleep() 메소드를 사용해 각각 100,500,2000으로 테스트 했다.
2. Thread 이름 = Producer - sendData / FirstConsumer - receiveData1 / SecondConsumer - receiveData2로 지정했다.
3. Consumer는 1000 millis로 고정하였고 log로 Thread의 Id와 Queue의 Size를 출력했다.
4. Queue의 사이즈를 통해 들어간 데이터와 빼내야하는 데이터를 볼 수 있다.

#### 1. 100 millis vs 1000 millis result
Producer 클래스는 0.1초 마다 메세지를 생성해 Queue에 집어넣는다.
FisrtConsumer와 SecondConsumer 클래스는 1초 마다 메세지를 Queue에서 빼낸다.
```
sendData : 7
12:44:12.409 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
12:44:12.409 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 6
sendData : 8
12:44:12.514 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
12:44:12.514 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 7
sendData : 9
12:44:12.620 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
12:44:12.620 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 8
receiveData1 : 0
12:44:12.680 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
12:44:12.680 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 8
sendData : 10
12:44:12.725 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
12:44:12.725 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 8
receiveData2 : 1
12:44:12.785 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
12:44:12.785 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 9
receiveData1 : 2
12:44:13.688 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
12:44:13.688 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 8
receiveData2 : 3
12:44:13.793 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
12:44:13.793 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 7
receiveData1 : 4
12:44:14.697 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
12:44:14.697 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 6
```

#### 2. 500 millis vs 1000 millis result
Producer 클래스는 0.5초 마다 메세지를 생성해 Queue에 집어넣는다.
FisrtConsumer와 SecondConsumer 클래스는 1초 마다 메세지를 Queue에서 빼낸다.
```
sendData : 6
13:12:05.674 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:12:05.674 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 5
13:12:06.170 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:12:06.170 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 7
13:12:06.185 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:12:06.185 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData1 : 6
13:12:06.680 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
13:12:06.680 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 0
sendData : 8
13:12:06.695 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:12:06.695 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
sendData : 9
13:12:07.197 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:12:07.197 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 1
receiveData2 : 7
13:12:07.197 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:12:07.197 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 1
receiveData1 : 8
sendData : 10
13:12:07.708 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
13:12:07.708 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:12:07.708 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 1
13:12:07.708 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 1
receiveData2 : 9
13:12:08.208 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:12:08.208 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 1
receiveData1 : 10
13:12:08.721 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
13:12:08.721 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 0
```

#### 3. 2000 millis vs 1000 millis result
Producer 클래스는 2초 마다 메세지를 생성해 Queue에 집어넣는다.
FisrtConsumer와 SecondConsumer 클래스는 1초 마다 메세지를 Queue에서 빼낸다.
```
sendData : 0
13:13:49.573 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:13:49.577 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 0
13:13:50.583 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:13:50.583 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 1
13:13:51.590 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:13:51.590 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData1 : 1
13:13:52.598 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
13:13:52.598 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 0
sendData : 2
13:13:53.603 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:13:53.603 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 2
13:13:54.615 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:13:54.615 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 3
13:13:55.608 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:13:55.608 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData1 : 3
13:13:56.609 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 id = 13 
13:13:56.609 [receiveData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - receiveData1 size = 0
sendData : 4
13:13:57.614 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:13:57.614 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 4
13:13:58.622 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:13:58.622 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 5
13:13:59.625 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData id = 12
13:13:59.625 [sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - sendData size = 0
```
-------

2. Blocking Queue를 이용한 Producer&Consumer Pattern (2)
+ 참고 사이트 : https://www.baeldung.com/java-blocking-queue
#### NumbersConsumer Class
```
import java.util.concurrent.BlockingQueue;
public class NumbersConsumer implements Runnable{
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue=queue;
        this.poisonPill=poisonPill;
    }

    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                if(number.equals(poisonPill)) {
                    return;
                }
                System.out.println("Thread Name : " + Thread.currentThread().getName() + " & result : " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

```

#### NumbersProducer Class
```
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
        }
        for (int j=0; j< poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
        }
    }
}
```

#### NumbersMain Class
```
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class NumbersMain {

    public static void main(String args[]) {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS/N_PRODUCERS;
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
```

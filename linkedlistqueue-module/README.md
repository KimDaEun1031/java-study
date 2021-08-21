# LinkedList Queue Module
Blocking Queue의 PC Pattern을 LinkedList Queue를 활용해 만들어보았다.

## PC Pattern vs SyncPattern

- Main과 Message Class는 같은 구조

#### Main
```
@Slf4j
public class PcPattern {
    @SneakyThrows
    public static void main(String[] args) {
    	
        //BlockingQueue -> LinkedList Queue
        Queue<Message> queue = new LinkedList<>();
        
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread threadProducer = new Thread(producer);
        threadProducer.setName("sendData");

        Thread[] threadConsumerArray = new Thread[2];
        for (int index = 0; index < threadConsumerArray.length; index++) {
            threadConsumerArray[index] = new Thread(consumer);
            threadConsumerArray[index].setName("receiveConsumerData"+(index+1));
            threadConsumerArray[index].start();
        }

        threadProducer.start();


        log.info("Test Start");
    }
}
```

#### Message
```
@ToString
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

1. **PC Pattern**
#### Producer
```
@Slf4j
//데이터를 생성하는 클래스
public class Producer implements Runnable {
	//공유 자원
    private Queue<Message> queue;

    public Producer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            Message msg = new Message("" + i);

            //해당 객체(queue)에 Lock 걸어 두개의 Thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                queue.add(msg);
                
                //wait를 가진 Thread 중 하나의 Thread를 실행상태로 만든다
                //Consumer 1,2 중 하나만 실행상태로
                queue.notify();
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            }
        }

        //exit 메세지를 생성한다
        Message msg = new Message("exit");

        //해당 객체(queue)에 Lock 걸어 두개의 Thread가 동시에 접근하는 것을 막는다
        synchronized (queue) {
            for (int i = 0; i < 5; i++) {
                queue.add(msg);
            }
            
            //wait를 가진 모든 Thread를 실행상태로 만든다
            queue.notifyAll();
        }

    }
}
```

#### Consumer
```
@Slf4j
//데이터를 소비하는 클래스
public class Consumer implements Runnable {
    //공유 자원
    private Queue<Message> queue;

    public Consumer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            Message msg;
			
            //성능 테스트를 위한 시간 측정
            long start = System.nanoTime();
            
            //해당 객체(queue)에 Lock 걸어 두개의 thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                //무한루프
                while (true) {
                
                    //데이터가 들어있지 않고 비었다면 데이터가 올 때 까지 대기한다
                    if (queue.isEmpty()) {
                        queue.wait();
                    }

                    //queue 안에 값이 있다면 값을 반환한 후에 제거하고 비어있다면 Null 값을 반환한다
                    msg = queue.poll();
                    log.info("msg = {}", msg);

                    //msg가 null이 아닐 경우 if문 바로 다음 문장들 수행
                    if (Objects.nonNull(msg)) {
                        //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
                        //큐에서 뽑은 값이 exit와 같다면 종료한다
                        if (msg.getMsg().equals("exit")) {
                            break;
                        }
                        log.info("msg is not null, msg = {}", msg.getMsg());
                    }
                         
                    log.info("queue size = {}", queue.size());
                }
            }
            
            log.info("total time = {}", System.nanoTime() - start);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Result
```
[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - msg = Message(msg=2)
[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - queue size = 3

[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - msg = Message(msg=3)
[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - queue size = 4

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg = Message(msg=0)
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg is not null, msg = 0

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - queue size = 3
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg = Message(msg=1)

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg is not null, msg = 1
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - queue size = 2

... 생략

[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - msg = Message(msg=9)
[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - queue size = 6

[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - msg = Message(msg=10)
[sendData] INFO org.daeun.linkedlistqueue.pcpattern.Producer - queue size = 7

[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg = Message(msg=4)
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg is not null, msg = 4
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - queue size = 6

[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg = Message(msg=5)
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg is not null, msg = 5
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - queue size = 5

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg = Message(msg=exit)
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - msg = Message(msg=exit)
```

#### Performance Test Result
```
모든 log는 주석 처리

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - total time = 2905840400
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.pcpattern.Consumer - total time = 2905820000
```

2. **Sync Pattern**
#### Producer
```
@Slf4j
//데이터를 생성하는 클래스
public class Producer implements Runnable {

    private Queue<Message> queue;

    public Producer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            Message msg = new Message("" + i);

            //해당 객체(queue)에 Lock 걸어 두개의 Thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                queue.add(msg);
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            }
        }

        //종료 용 exit 메세지를 생성한다
        Message msg = new Message("exit");

        //해당 객체(queue)에 Lock 걸어 두개의 Thread가 동시에 접근하는 것을 막는다
        synchronized (queue) {
            for (int i = 0; i < 5; i++) {
                queue.add(msg);
            }
        }

    }
}
```

#### Consumer
```
@Slf4j
//데이터를 소비하는 클래스
public class Consumer implements Runnable {

    private Queue<Message> queue;

    public Consumer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            Message msg;

            long start = System.nanoTime();
            while (true) {
                //해당 객체(queue)에 Lock 걸어 두 개의 Thread가 동시에 접근하는 것을 막는다
                synchronized (queue) {

                    //queue 안에 값이 있다면 값을 반환한 후에 제거하고 비어있다면 Null 값을 반환한다
                    msg = queue.poll();
                    log.info("msg = {}", msg);

                    //msg가 null이 아닐 경우 if문 바로 다음 문장들 수행
                    if (Objects.nonNull(msg)) {
                        //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
                        //큐에서 뽑은 값이 exit와 같다면 종료한다
                        if (msg.getMsg().equals("exit")) {
                            break;
                        }
                        log.info("msg is not null, msg = {}", msg.getMsg());
                    }
                    log.info("queue size = {}", queue.size());
                }
            }
            log.info("total time = {}", System.nanoTime() - start);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Result
```
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = null
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 0

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = null
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 0

[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - msg = Message{msg='0'}
[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - queue size = 1

[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = Message{msg='0'}
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg is not null, msg = 0
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 0

[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = null
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 0

... 생략

[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - msg = Message{msg='4'}
[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - queue size = 4

[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - msg = Message{msg='5'}
[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - queue size = 5

[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - msg = Message{msg='6'}
[sendData] INFO org.daeun.linkedlistqueue.syncpattern.Producer - queue size = 6

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = Message{msg='1'}
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg is not null, msg = 1
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 5

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = Message{msg='2'}
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg is not null, msg = 2
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 4

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = Message{msg='3'}
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg is not null, msg = 3
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 3

... 생략

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = null
[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - queue size = 0

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = Message{msg='exit'}
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - msg = Message{msg='exit'}
```

#### Performance Test Result
```
모든 log는 주석 처리

[receiveConsumerData1] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - total time = 3161334500
[receiveConsumerData2] INFO org.daeun.linkedlistqueue.syncpattern.Consumer - total time = 3161320700
```

------

## PC Pattern과 Sync Pattern의 차이점
PC Pattern은 Thread의 상태를 제어하는 wait()와 notify() 메소드를 사용하지만 Sync Pattern은 제어하는 메소드를 사용하지 않는다.

Perfromance Test 시 차이가 거의 없지만 PC Pattern이 조금 더 빠르다.

Sync Pattern이 조금 더 느린 이유는 병목현상이 일어나기 때문이다.
현재 공유 자원은 Queue인데 데이터를 빼고 넣고 하는 구조를 Queue로 쓰는데 synchronized를 써서 쓸 때마다 lock이 걸리게 해놓았다.
병목현상은 과도한 락으로 인해 용량이나 성능이 저하되는 현상이기에 PC Pattern은 notify()에서 Thread를 제어해 주지만 Sync Pattern은 없기에 너무 많고 빠르게 synchronized가 실행되서 Sync Pattern이 조금 더 느린 것이다.

## if (Objects.nonNull(msg))를 사용하는 이유
Blocking Queue에서는 Queue의 값을 반환 후 제거하는 take()를 써서 종료 조건을 만들었다.

같은 방법으로 LinkedList Queue로 사용할 시에 값을 반환 후에 Queue에서 제거하는 메소드가 poll()이 있었는데 Queue 안이 비어있다면 무한정 대기하는 take()와 다르게 poll()은 비어있다면 null 값을 반환했다.

종료 조건이 [ Queue 값이 exit 라면 종료 ] -> if (msg.getMsg().equals("exit)) { break; } 일 때 nonNull을 쓰지 않고 쓰면 nullPointerException이 발생했다.


-> nullPointerException의 발생을 막기위해서

## wait(), notify(), notifyAll()
- wait() - 다른 Thread에게 제어권을 넘겨주고 실행 상태에서 대기상태가 되는 메소드
- notify() - wait를 가진 Thread 중 하나의 Thread를 대기에서 실행 상태로 풀어주는 메소드
- notifyAll() - wait를 가진 모든 Thread를 대기에서 실행 상태로 풀어주는 메소드

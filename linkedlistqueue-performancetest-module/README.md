# LinkedList Queue Performance Test Modul
LinkedList Queue에서 연습했던 PC Pattern에 조건을 주어서 만들어보았다.
**조건**
Producer와 Consumer는 각각 100만 개를 만들고 소비해야한다.
Producer는 100개를 생성할 때마다 1ms Sleep를 건다.
Consumer는 10개를 소비할 때마다 1ms Sleep를 건다.
Producer와 Consumer를 따로 진행했을 경우 각각 약 1초의 시간이 걸려야한다.(1초를 살짝 넘겨도 된다.)
-> 따로 진행 : Producer가 먼저 넣고 Consumer 진행 같은 경우

## LinkedList Queue 1초에 100만 번 생성&소비

#### Main
```
@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {

        //프로듀서가 생성한 메세지를 넣고 콘슈머가 메세지를 뺄 수 있게 메세지를 저장하는 공간
        Queue<Message> queue = new LinkedList<>();
        //데이터를 생성하는 클래스를 생성
        Producer producer = new Producer(queue);
        //데이터를 소비하는 클래스를 생성
        Consumer consumer = new Consumer(queue);


        //Thread Producer 배열 생성
        Thread[] threadProducerArray = new Thread[11];
        for (int index = 0; index < threadProducerArray.length; index++) {
            threadProducerArray[index] = new Thread(producer);
            threadProducerArray[index].setName("sendData" + (index + 1));
            threadProducerArray[index].start();
        }


        //Thread Consumer 배열 생성
        Thread[] threadConsumerArray = new Thread[100];
        for (int index = 0; index < threadConsumerArray.length; index++) {
            threadConsumerArray[index] = new Thread(consumer);
            threadConsumerArray[index].setName("receiveConsumerData"+(index+1));
            threadConsumerArray[index].start();
        }

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

#### Producer
```
@Slf4j
public class Producer implements Runnable {

    private Queue<Message> queue;
    private AtomicInteger counter = new AtomicInteger();

    public Producer(Queue<Message> q) {
        this.queue = q;
    }

    @SneakyThrows
    @Override
    public void run() {
        long start = System.nanoTime();

        int localCount = 0;
        while (true) {
            int index = counter.incrementAndGet();
            Message msg = new Message("" + index);

            //해당 객체(queue)에 Lock 걸어 여러 개의 thread가 동시에 접근하는 것을 막는다
            synchronized (queue) {
                queue.add(msg);
                log.info("msg = {}", msg);
                log.info("queue size = {}", queue.size());
            }

            //localCount를 100으로 나누었을 때 0으로 떨어지면 0.001초 대기한다
            if(++localCount % 100 == 0) {
                Thread.sleep(1);
                log.info("localCount = {}, count ={}", localCount, counter.get());
            }


            //counter의 값이 100만보다 크거나 같다면 종료
            if (counter.get() >= 1000000)
                break;
        }

        log.info("Producer total time = {}", System.nanoTime() - start);
        log.info("Result : count = {}", counter.get());
        log.info("queue size = {}", queue.size());

        //exit 메세지를 생성한다
        Message msg = new Message("exit");

        synchronized (queue) {
            for (int i = 0; i < 10; i++) {
                queue.add(msg);
            }
        }
    }
}
```

#### Consumer
```
@Slf4j
public class Consumer implements Runnable {

    private Queue<Message> queue;

    public Consumer(Queue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        int localCount = 0;
        long start = System.nanoTime();

        try {
            Message msg;
            
            while (true) {
                //해당 객체(queue)에 Lock 걸어 여러 개의 thread가 동시에 접근하는 것을 막는다
                synchronized (queue) {
                    //queue 안에 값이 있다면 값을 반환한 후에 제거하고 비어있다면 Null 값을 반환한다
                    msg = queue.poll();
                    log.info("msg = {}", msg);
                }
                
                //msg가 null이 아닐 경우 if문 바로 다음 문장들 수행
                if (Objects.nonNull(msg)) {
                    //localCount를 10으로 나누었을 때 0으로 떨어지면 0.001초로 대기한다
                    if (++localCount % 10 == 0) {
                        Thread.sleep(1);
                        log.info("localCount = {}, queue size = {}", localCount, queue.size());
                    }

                    //큐에서 뽑은 값이 exit와 같지 않다면 계속 실행한다
                    //큐에서 뽑은 값이 exit와 같다면 종료한다
                    if (msg.getMsg().equals("exit")) {
                        break;
                    }
                }
            }

            log.info("Consumer total time = {}", System.nanoTime() - start);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

#### Result
```
//synchronized 블럭 안의 Log는 주석 처리
//Consumer queue는 항상 값이 남아있음 - exit 때문에

//Producer Individual Result
[sendData10] INFO org.daeun.linkedlistqueueperformancetest.pcpattern.Producer - queue size = 1000000
[sendData1] INFO org.daeun.linkedlistqueueperformancetest.pcpattern.Producer - Producer total time = 1354461300 //1.3초 

//Consumer Individual Result
[receiveConsumerData53] INFO org.daeun.linkedlistqueueperformancetest.pcpattern.Consumer - Consumer total time = 1126914700 // 1.1초

//Result
[sendData4] INFO org.daeun.linkedlistqueueperformancetest.pcpattern.Producer - Producer total time = 1368416200
[receiveConsumerData87] INFO org.daeun.linkedlistqueueperformancetest.pcpattern.Consumer - Consumer total time = 1373566700
```

----

## AtomicInteger는 공유자원인데 Main이 아닌 Producer에 들어가있나?
Producer에서만 쓰기 때문에 굳이 Main에서 생성해서 쓸 필요가 없다.
정 Consumer에서도 써야한다 하면 처음부터 Main에서 생성해서 쓰면 되지만 이미 Producer 쪽에 있다면 Consumer 매개변수에 AtomicInteger를 추가해 Producer의 AtomicInteger를 사용한다.

# java-study

# Spring Maven Multi-Module Project Setting(Intellij)
https://zzang9ha.tistory.com/302

---

# Thread
하나의 프로세스에서 독립적으로 실행되는 하나의 일(or 작업)의 단위  
+ 참고하면 좋은 사이트 : https://ict-nroo.tistory.com/41

#### JAVA Thread 생명주기
![다운로드 (1)](https://user-images.githubusercontent.com/73865700/126848650-2a7a0b9f-3194-4dfe-b65a-34dd64103507.png)  
Thread 객체 생성 -> start() 호출 -> run()호출 -> [실행 상태 <-> 실행 가능 상태] <-> [대기 상태] by sleep() 등의 메소드 -> terminated

#### Thread 정보
||타입|관련 메소드|내용|
|----|----|----|----|
|name|String|get,set|스레드 이름|
|id|int|get|스레드 고유 식별자 번호|
|PC(Program Count)|int||현재 실행 중인 스레드 코드의 주소|
|State|int|get|스레드의 상태 6가지 중 하나|
|Priority|int|get,set|스레드 스케줄링 때 사용되는 값으로 1~10(기본:5 / 최상위:10)|
|ThreadGroup|int|get|여러 개의 스레드가 하나의 그룹을 형성할 수 있는데, 스레드가 속한 그룹|
|Register Stack|||스레드가 실행되는 동안 레지스터들의 값|

#### Thread 상태
|State|Description|
|----|----|
|NEW|스레드가 생성된 상태, 아직 start() 되지 않음|
|RUNNABLE|start()가 호출되어 실행대기, run() 하면 RUNNING(CPU 점유)이 됨, Runnable pool에 모여있음|
|WAITING|일시 정지, 다른 스레드의 통지를 기다림|
|TIMED_WAITING|일시 정지, 주어진 시간동안 기다림|
|BLOCK|일시 정지, 사용하려는 객체의 lock이 풀릴 때까지 대기 등..|
|TERMINATED|실행마치고 종료, run()이 끝나면 terminated 상태되면서 소멸|

#### Thread 종류
1. 사용자 정의 Thread : Thread 클래스를 상속받거나 Runnable 인터페이스를 구현해서 사용자가 직접 Tread를 생성하고 사용하는 것
2. 메인 Thread : main() 메소드를 호출해서 실행하는 역할 기본 Thread

#### Process
메모리에 적재되어 Processer에 의해 실행 중인 프로그램

#### Process 종류
1. Foreground Process : 눈으로 확인 가능한 범위에서 진행되는 프로세스
   ex) 작업관리자의 응용프로그램 탭에서 확인 가능한 프로세스
2. Background Process : 응용 프로그램의 실행을 뒤에서 보조하는 프로세스
   ex) 작업관리자의 백그라운드 프로세스에서 확인 가능
   
#### Processer
||하드웨어|소프트웨어|
|----|----|----|
|정의|컴퓨터 내에서 프로그램을 수행하는 하드웨어 유닛이다. 이는 중앙 처리 장치(Central Processing Unit)을 뜻하며 폰노이만 아키텍쳐에 의해 만들어졌다면 적어도 하나 이상의 ALU(연산장치, Arithmetic Logic Unit)와 처리 레지스터(Register)를 내장하고 있어야한다.|데이터 포맷을 변환하는 역할을 수행하는 데이터 프로세싱 시스템(데이터 처리 시스템)을 의미한다.|

#### Thread Description
+ 참고 영상 : https://www.youtube.com/watch?v=iks_Xb9DtTM  
예전 컴퓨터에서는 Multi Tasking이 되지 않고 하나씩 실행되었다. 예로 들어서 게임 다운로드를 한다고 하면 다운로드하는 그 장면에서 마우스와 키보드 모두 사용이 안되서 아무것도 할 수 없는 상태가 된다.(지금처럼 브라우저나 다른 프로그램을 동시에 실행하지 못함)  
여러 프로세스를 함께 돌리는 작업은 병렬적, 동시적 혹은 2개의 혼합으로 이루어진다.  

||동시적(Concurrency)|병렬적(Parallelism)|
|----|----|----|
|정의|여러 작업을 돌아가면서 일부분씩 진행하는 것|프로세스 하나에 코어 여러 개가 달려서 각각 동시에 작업을 수행하는 것|
|설명|과정이 빨라서 동시에 작업하는 것처럼 느껴진다. 진행 중인 작업을 바꾸는 것을 Context-Switching이라고 한다.| 멀티코어(듀얼(x2), 쿼드(x4), 옥타(x8) 등) 프로세서가 달린 컴퓨터에서 할 수 있는 방식이다. CPU의 속도나 발열 등 물리적 제약 때문에 예전만큼 빠르게 발전하지 못하자 그 대안으로 코어를 여러개 달아서 작업을 분담할 수 있도록 만들었다.|

컴퓨터가 여러 프로세스를 돌릴 수 있게 되면서 브라우저같은 프로그램을 실행하는 도중에도 게임을 다운로드 받으면서 유튜브로 들어가면 영상 정보를 받아오거나 웹툰 사이트로 들어가면 이미지 정보를 받아오는 등의 프로세스를 처리해야하는 작업을 동시에 진행할 필요성이 생겼는데 이렇게 한 프로세스 안에서 여러 갈래의 작업들을 동시에 진행하는 갈래를 **Thread(스레드)** 라고 부른다.

+ 비유 설명
레스토랑에 비유하면 요리사 = 프로세서 | 요리메뉴 = 프로세스 | 요리과정 = 스레드 | 조리대 = 메모리 라고 생각하면 된다.  
요리메뉴는 임시로 라면 / 김밥 / 떡볶이로 정한다.  
컴퓨터는 프로세스마다 자원을 분할해서 할당한다. 라면 조리 세션 / 김밥 조리 세션 / 떡볶이 조리 세션으로 나누었다.
세션을 나누어서 요리사가 혼자서 돌아다니면서 동시적으로 하든 여럿이서 병렬적으로 하든, 혹은 섞어서 하든 계속해서 메뉴들을 만들어낸다.
김밥 조리를 생각해보면 햄, 당근 썰고 / 햄 굽고 / 당근 볶고 / 달걀지단 부치고 / 밥 양념해서 식히고 / 김 위에 밥하고 재료 넣어서 말고 / 참기를을 바른 뒤 끝낸다.
/로 나눈 요리과정이 하나의 스레드라고 보면 된다.
요리사는 햄을 굽는 스레드를 진행하면서 밥 양념해서 식히는 스레드도 진행할 수 있다.
하나의 요리메뉴는 같은 조리대에서 진행하는데 같은 조리대가 아니라 다른 조리대에서 햄을 굽고 다른 조리대에서 밥 양념을 하면 같은 조리대에서 작업하는 것보다 효율성이 떨어진다.
같은 메뉴을 만들 때 같은 공간과 같은 장비를 사용하는 것 = 각각의 프로세스에 할당된 자원들을 스레드가 함께 사용하는 것

같은 메뉴을 만들 때 같은 공간과 같은 장비를 사용하는 것
![f](https://user-images.githubusercontent.com/73865700/126625189-12df2be1-bf17-49f6-b320-27c7377ab6a1.png)

각각의 프로세스에 할당된 자원들을 스레드가 함께 사용하는 것
![p](https://user-images.githubusercontent.com/73865700/126625232-f227f6dc-e729-4db5-90c3-e1e8d30862ad.png)

#### Thread 예제
RamenProgram
```
public class RamenProgram {

    public static void main(String args[]) {
        try {
            RamenCook ramenCook = new RamenCook(4);
            new Thread(ramenCook, "A").start();
            new Thread(ramenCook, "B").start();
            new Thread(ramenCook, "C").start();
            new Thread(ramenCook, "D").start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class RamenCook implements Runnable {

    private int ramenCount;
    private String[] burners = {"_", "_", "_", "_"};

    public RamenCook(int count) {
        ramenCount = count;
    }

    @Override
    public void run() {
        while (ramenCount > 0) {

            synchronized (this) {
                ramenCount--;
                System.out.println(
                    Thread.currentThread().getName()
                    + ": " + ramenCount + "개 남은"
                );
            }

            for (int i = 0; i < burners.length; i++) {
                if (!burners[i].equals("_")) continue;

                synchronized (this) {
                    burners[i] = Thread.currentThread().getName();
                    System.out.println(
                        "                 "
                        + Thread.currentThread().getName()
                        + ": [" + (i + 1) + "]번 버너 ON"
                    );
                    showBurners();
                }

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                synchronized (this) {
                    burners[i] = "_";
                    System.out.println(
                        "                                    "
                        + Thread.currentThread().getName()
                        + ": [" + (i + 1) + "]번 버너 OFF"
                    );
                    showBurners();
                }
                break;
            }
            try {
                Thread.sleep(Math.round(1000 * Math.random()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showBurners() {
        String stringToPrint
            = "                                           ";
        for (int i = 0; i < burners.length; i++) {
            stringToPrint += (" "+ burners[i]);
        }
        System.out.println(stringToPrint);
    }
}
```
Result
```
A: 3개 남은
                 A: [1]번 버너 ON
                                            A _ _ _
C: 2개 남은
                 C: [2]번 버너 ON
                                            A C _ _
D: 1개 남은
                 D: [3]번 버너 ON
                                            A C D _
B: 0개 남은
                 B: [4]번 버너 ON
                                            A C D B
                                    C: [2]번 버너 OFF
                                            A _ D B
                                    D: [3]번 버너 OFF
                                            A _ _ B
                                    A: [1]번 버너 OFF
                                            _ _ _ B
                                    B: [4]번 버너 OFF
                                            _ _ _ _
                                            
```

#### Thread Scheduling
멀티스레드의 순서를 정하는 것
+ 참고 사이트 : https://coding-factory.tistory.com/569

#### Thread Scheduling 방식
||우선 순위(Priority) 방식|순환 할당(Round-Robin) 방식|
|----|----|----|
|정의|우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케쥴링 하는 것|시간 할당량(Time Slice)을 정해서 하나의 스레드를 정해진 시간만큼 실행하고 다시 다른 스레드를 실행하는 방식|
|설명|setPriority() 메소드를 사용하여 우선순위를 설정한다.|JVM(Java Virtual Machine)에 의해 결정되기 때문에 임의로 설정할 수 없다.|

#### Thread Scheduling 예제
```
public class ThreadScheduling {
    public static void main(String[] args) {
        for(int i=0;i<=5;i++) {
            Thread thread = new Scheduled("[ Thread " + i + " ]");
            if (i == 5)
                thread.setPriority(Thread.MAX_PRIORITY); //MAX 우선순위 = 10 
            else                                         // Thread.NORM_PRIORITY = 5 기본
                thread.setPriority(Thread.MIN_PRIORITY); //MIN 우선순위 = 1
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

```
RESULT
```
[ Thread 5 ] Thread_Start 
[ Thread 4 ] Thread_Start 
[ Thread 1 ] Thread_Start 
[ Thread 3 ] Thread_Start 
[ Thread 2 ] Thread_Start 
[ Thread 0 ] Thread_Start
```

# Multi Thread
+ 하나의 프로그램에서 동시에 여러 개의 일을 수행할 수 있도록 해주는 것
+ 하나의 프로세스를 다수의 실행단위로 구분하여 자원을 공유하고 자원의 생성과 관리의 중복성을 최소화하여 수행 능력을 향상시키는 것을 **멀티스레딩**이라고 한다.

#### Multi Thread 사용 이유
+ 프로세스를 이용하여 동시에 처리하던 일을 스레드로 구현할 경우 메모리 공간과 시스템 자원 소모가 줄어든게 된다.
+ 스레드 간의 통신이 필요한 경우에도  별도의 자원을 이용하는 것이 아니라 **전역 변수의 공간** or 동적으로 할당된 공간인 **힙(Heap) 영역**을 이용하여 데이터를 주고 받을 수 있다.
-> 프로세스 간 통신 방법에 비해 스레드 간의 통신 방법이 훨씬 간단하다.

+ 스레드의 **문맥 교환(Context-Switching)** 은 프로세스의 문맥교환 과 달리 **캐시 메모리를 비울 필요가 없기 때문에** 더 빠르다.
-> **시스템의 처리량** 이 향상되며 자원소모가 줄어들어 프로그램의 응답시간 단축된다.

#### Single Thread(단일 스레드) vs Multi Thread(다중 스레드)
![다운로드](https://user-images.githubusercontent.com/73865700/126851251-b26e4874-eff7-4319-9123-4ca5c974c615.jpg)
#### Single Thread 장점
1) 자원 접근에 대한 동기화를 신경쓰지 않아도 된다.  
   여러 개의 스레드가 공유된 자원을 사용할 경우 각 스레드가 원하는 결과를 얻게 하려면 공용자원에 대한 접근이 통제되야하는 작업 -> 비용 발생 -> 단일 스레드는 필요치 않음
   
2) 문맥 교환(Context-Switch) 작업을 요구하지 않는다.  
   문맥교환은 여러 개의 프로세스가 하나의 프로세서를 공유할 때 발생하는 작업 -> 많은 비용 필요
   
3) CPU만을 사용하는 계산 작업에 더 효율적이다.
   두 개의 작업을 하나의 스레드로 처리하는 경우와 두 개의 스레드로 처리하는 경우 후자의 경우에는 짧은 시간동안 2개의 스레드가 번갈아가면서 작업을 수행하기에 동시에 두 작업이 처리되는 것과 같    이 느낌이 든다. 하지만 두 개의 스레드로 작업하는 시간이 더 걸릴 수도 있는데, 그 이유는 스레드 간의 문맥 전환에 시간이 걸리기 때문이다. 

#### Single Thread 단점
1) 여러 개의 CPU를 활요하지 못한다.
   프로세서를 최대한 활용하게 하려면 cluster 모듈을 사용하거나, 외부에서 여러 개의 프로그램 인스턴스를 실행시키는 방법을 사용해야 한다.

#### Multi Thread 장점
1) 새로운 프로세스를 생성하는 것보다 기존 프로세스에서 스레드를 생성하는 것이 빠르다.
   프로세스 생성은 많은 시간과 자원을 소비 -> 이러한 단점을 최소화 시킨 **경량화의 프로세스 = 스레드** 를 만듬

2) 프로세스의 자원의 상태를 공유하여 효율적으로 운영이 가능하다.
   Multi Thread에서 스레드 간 스택 영역만 비공유하고 데이터 영역과 힙 영역을 공유 -> 문맥 교환 시 데이터 영역과 힘을 올리고 내릴 필요가 없음

3) 프로세스의 문맥 교환보다 스레드의 문맥 교환이 빠르다.
   스레드 사이에서의 데이터 교환은 특별한 기법이 필요 없음
   
#### Multi Thread 단점
1) 하나의 스레드만 실행 중일 때에는 실행시간이 되려 지연될 수 있다.


2) 멀티 스레딩을 위해 운영체제의 지원이 필요하다.
   스레드는 프로세스의 자원을 공유하는데 운영체제의 자원 관리 기능으로 프로세스에 자원을 할당시켜주기 때문에 지원이 필요함

3) 멀티 스레드 환경에서는 동기화 작업이 필요하다.
   동기화를 통해서 작업처리순서와 공유 자원에 대한 접근 컨트롤하는데 공유 자원인 부분은 어떤 스레드가 다른 스레드에서 사용 중인 변수나 자료구조에 접근하여 엉뚱한 값을 읽어오거나 수정할 수 있
   기에 synchronized 키워드를 이용해 동기화를 해야한다. 동기화를 사용하지 않으면 Thread-safe(프로그램이 문제없이 원활하게 실행되는 것)가 되지 않기 때문에 사용을 해야한다. 하지만 동기화 작업
   으로 병목 현상(과도한 락으로 인해 용량이나 성능이 저하되는 현상)이 발생할 수 있기에 공유 자원이 아닌 부분은 할 필요가 없다.
   
#### Multi Thread 예제
1. java.lang.thread 클래스를 직접 객체화 해서 생성하는 방법
```
public class ThreadSynchronized {

    public static void main (String[] args) {

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
```

2. Thread를 상속해서 하위클래스를 만들어 생성하는 방법
```
public class MultiThreadPractice {
    public static void main(String[] args) throws Exception {
    
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
```

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

#### FisrtConsumer & SecondConsumer Class
```
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class FirstConsumer implements Runnable {
    private BlockingQueue<Message> queue;

    public FirstConsumer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            Message msg;

            while ((msg = queue.take()).getMsg() !="exit") {
                Thread.sleep(10);
                System.out.println("Consumed"+msg.getMsg());
                log.info("Consumer thread id : "+Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

```
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class SecondConsumer implements Runnable {
    private BlockingQueue<Message> queue;

    public SecondConsumer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            Message msg;

            while ((msg = queue.take()).getMsg() !="exit") {
                Thread.sleep(10);
                System.out.println("SecondConsumed"+msg.getMsg());
                log.info("SecondConsumer thread id : "+Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### Producer Class
```
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Producer implements Runnable {
    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> q) {
        this.queue=q;
    }

    @Override
    public void run() {
        for(int i=0;i<31;i++) {
            Message msg = new Message(""+i);
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced"+msg.getMsg());
                log.info("Producer thread id : "+Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### Main Class
```
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(30);
        Producer producer = new Producer(queue);
        FirstConsumer consumer = new FirstConsumer(queue);
        SecondConsumer secondConsumer = new SecondConsumer(queue);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        Thread threadSecondConsumer = new Thread(secondConsumer);

        threadProducer.start();
        threadConsumer.start();
        threadSecondConsumer.start();

        System.out.println("Test Start");

    }
}
```

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

# Thread TimeUnit Test
1. Capacity는 10으로 하였고 Producer 클래스에서 Thread.sleep() 메소드를 사용해 각각 100,500,2000으로 테스트 했다.
2. Thread 이름 = Producer - sendData / FirstConsumer - receiveData1 / SecondConsumer - receiveData2로 지정했다.
3. Consumer는 1000 millis로 고정하였고 log로 Thread의 Id와 Queue의 Size를 출력했다.
4. Queue의 사이즈를 통해 들어간 데이터와 빼내야하는 데이터를 볼 수 있다.

#### 100 millis vs 1000 millis result
Producer 클래스는 0.1초 마다 메세지를 생성해 Queue에 집어넣는다.
FisrtConsumer와 SecondConsumer 클래스는 1초 마다 메세지를 Queue에서 빼낸다.
```
sendData : 7
12:44:12.409 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
12:44:12.409 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 6
sendData : 8
12:44:12.514 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
12:44:12.514 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 7
sendData : 9
12:44:12.620 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
12:44:12.620 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 8
receiveData1 : 0
12:44:12.680 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
12:44:12.680 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 8
sendData : 10
12:44:12.725 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
12:44:12.725 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 8
receiveData2 : 1
12:44:12.785 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
12:44:12.785 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 9
receiveData1 : 2
12:44:13.688 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
12:44:13.688 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 8
receiveData2 : 3
12:44:13.793 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
12:44:13.793 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 7
receiveData1 : 4
12:44:14.697 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
12:44:14.697 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 6
```

#### 500 millis vs 1000 millis result
Producer 클래스는 0.5초 마다 메세지를 생성해 Queue에 집어넣는다.
FisrtConsumer와 SecondConsumer 클래스는 1초 마다 메세지를 Queue에서 빼낸다.
```
sendData : 6
13:12:05.674 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:12:05.674 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 5
13:12:06.170 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:12:06.170 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 7
13:12:06.185 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:12:06.185 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData1 : 6
13:12:06.680 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
13:12:06.680 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 0
sendData : 8
13:12:06.695 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:12:06.695 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
sendData : 9
13:12:07.197 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:12:07.197 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 1
receiveData2 : 7
13:12:07.197 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:12:07.197 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 1
receiveData1 : 8
sendData : 10
13:12:07.708 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
13:12:07.708 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:12:07.708 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 1
13:12:07.708 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 1
receiveData2 : 9
13:12:08.208 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:12:08.208 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 1
receiveData1 : 10
13:12:08.721 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
13:12:08.721 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 0
```

#### 2000 millis vs 1000 millis result
Producer 클래스는 2초 마다 메세지를 생성해 Queue에 집어넣는다.
FisrtConsumer와 SecondConsumer 클래스는 1초 마다 메세지를 Queue에서 빼낸다.
```
sendData : 0
13:13:49.573 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:13:49.577 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 0
13:13:50.583 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:13:50.583 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 1
13:13:51.590 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:13:51.590 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData1 : 1
13:13:52.598 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
13:13:52.598 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 0
sendData : 2
13:13:53.603 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:13:53.603 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 2
13:13:54.615 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:13:54.615 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 3
13:13:55.608 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:13:55.608 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData1 : 3
13:13:56.609 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 id = 13 
13:13:56.609 [receiveData1] INFO org.daeun.blockingqueue.pcpattern.FirstConsumer - receiveData1 size = 0
sendData : 4
13:13:57.614 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:13:57.614 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0
receiveData2 : 4
13:13:58.622 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 id = 14 
13:13:58.622 [receiveData2] INFO org.daeun.blockingqueue.pcpattern.SecondConsumer - receiveData2 size = 0
sendData : 5
13:13:59.625 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData id = 12
13:13:59.625 [sendData] INFO org.daeun.blockingqueue.pcpattern.Producer - sendData size = 0

```

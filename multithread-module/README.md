# MultiThread Module

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

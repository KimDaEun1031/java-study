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

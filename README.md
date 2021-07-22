# java-study

# Spring Maven Multi-Module Project Setting(Intellij)
https://zzang9ha.tistory.com/302

---

# Thread
하나의 프로세스에서 독립적으로 실행되는 하나의 일(or 작업)의 단위

#### Thread 종류
1. 사용자 정의 Thread : Thread 클래스를 상속받거나 Runnable 인터페이스를 구현해서 사용자가 직접 Tread를 생성하고 사용하는 것
2. 메인 Thread : main() 메소드를 호출해서 실행하는 역할 기본 Thread

#### Process
현재 실행 중인 응용 프로그램의 최소 실행 단위

#### Process 종류
1. Foreground Process : 눈으로 확인 가능한 범위에서 진행되는 프로세스
   ex) 작업관리자의 응용프로그램 탭에서 확인 가능한 프로세스
2. Background Process : 응용 프로그램의 실행을 뒤에서 보조하는 프로세스
   ex) 작업관리자의 백그라운드 프로세스에서 확인 가능
   
#### Thread Description
+ 참고 영상 : https://www.youtube.com/watch?v=iks_Xb9DtTM
예전 컴퓨터에서는 Multi Tasking이 되지 않고 하나씩 실행되었다. 예로 들어서 게임 다운로드를 한다고 하면 다운로드하는 그 장면에서 마우스와 키보드 모두 사용이 안되서 아무것도 할 수 없는 상태가 된다.(지금처럼 브라우저나 다른 프로그램을 동시에 실행하지 못함)  
여러 프로세스를 함께 돌리는 작업은 병렬적, 동시적 혹은 2개의 혼합으로 이루어진다.
||동시적(Concurrency)|병렬적(Parallelism)|
|----|----|----|
|정의|여러 작업을 돌아가면서 일부분씩 진행하는 것|프로세스 하나에 코어 여러 개가 달려서 각각 동시에 작업을 수행하는 것|
|설명|과정이 빨라서 동시에 작업하는 것처럼 느껴진다. 진행 중인 작업을 바꾸는 것을 Context-Switching이라고 한다.|멀티코어(듀얼(x2), 쿼드(x4), 옥타(x8) 등) 프로세서가 달린 컴퓨터에서 할 수 있는 방식이다. CPU의 속도나 발열 등 물리적 제약 때문에 예전만큼 빠르게 발전하지 못하자 그 대안으로 코어를 여러개 달아서 작업을 분담할 수 있도록 만들었다.|

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



package org.daeun.question.greedyalgorithm;

import java.util.Scanner;

public class VendingMachineIgnorant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int itemPrice = 707;
        int[] numberOfCoins = new int[6];
//        for (int i = 0; i < numberOfCoins.length; i++) {
//            numberOfCoins[i] = sc.nextInt();
//        }
        numberOfCoins[0] = 4;
        numberOfCoins[1] = 2;
        numberOfCoins[2] = 2;
        numberOfCoins[3] = 6;
        numberOfCoins[4] = 3;
        numberOfCoins[5] = 4;

        sc.close();


        int[] coins = {500, 100, 50, 10, 5, 1};

        //나누는 값
        int index = coins.length - 2;
        //빠지는 값
        int units = coins.length - 1;
        int checkTemp = 0;

        while (index >= 0) {
            for (int i = 1; i <= numberOfCoins[units]; i++) {
                itemPrice = itemPrice - coins[units];
                //itemPrice가 코인 배열의 값들 보다 작을 경우 사용되지 않으므로 0
                if (itemPrice < coins[index]) {
                    numberOfCoins[index] = 0;
                }
                int check = itemPrice % coins[index];

                if (check == 0) {
                    checkTemp = i;
                }

                if (i == numberOfCoins[units]) {
                    //사용하지 않은 동전의 수를 구하기
                    int min = numberOfCoins[units] - checkTemp;
                    numberOfCoins[units] = checkTemp;
                    //사용하지 않은 돈을 복구
                    if (min != 0) {
                        itemPrice += coins[units] * min;
                    }
                    //itemPrice가 0이거나 0보다 작을 경우
                    if (itemPrice <= 0) {
                        //0이 되었을 때 끝난 배열 값에 저장된 i 값 넣어주기
                        numberOfCoins[units] = checkTemp;
                        //While문 종료를 위한 감소
                        index--;
                        break;
                    }
                    //500 처리
                    if(index == 0) {
                        //100을 처리 후에 남은 itemPrice에 코인 배열의 마지막 값 500을 넣고 나눠준다.
                        int divide = itemPrice / coins[index];
                        //마지막 0번에 넣어주고 종료
                        numberOfCoins[index] = divide;
                        index--;
                        break;
                    }
                    //초기화
                    checkTemp = 0;
                    units--;
                    index--;
                    break;

                }
            }

        }

        for (int i = 0; i < coins.length; i++) {
            System.out.print(numberOfCoins[i] + " ");
        }

    }
}

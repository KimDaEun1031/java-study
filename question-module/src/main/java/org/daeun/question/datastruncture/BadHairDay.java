package org.daeun.question.datastruncture;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadHairDay {
    public static void main(String[] args) {
        /*
        i번 소들이 볼 수 있는 머리 모양의 수를 Ci 이라고 할 때, C1부터 CN 까지의 합을 출력하는 프로그램을 작성하라.

        입력의 첫 번째 행에는 N 이 입력된다.
        그 다음 줄부터 N 개의 숫자가 행을 구분하여 주어지며, 해당 행의 i번째 숫자는 hi를 뜻한다.

        C1 부터 CN 까지의 합을 한 줄에 출력한다.
        */

        Scanner sc = new Scanner(System.in);

        List<Integer> cowList = new ArrayList<>();
        int cowListSize = sc.nextInt();

        for (int i = 0; i < cowListSize; i++) {
            int cowListNumber = sc.nextInt();
            cowList.add(cowListNumber);
        }

        int count = 0;
        int sum[] = new int[cowList.size()];
        int result = 0;
        int compareNum;

        for (int i = 0; i < cowList.size(); i++) {
            for (compareNum = 1+i; compareNum < cowList.size(); compareNum++) {
                if (cowList.get(i) > cowList.get(compareNum)) {
                    count++;
                    if(compareNum == cowList.size() -1) {
                        sum[i] = count;
                        break;
                    }
                } else {
                    sum[i] = count;
                    count = 0;
                    break;
                }

            }

        }

        for (int j : sum) {
            result += j;
        }

        System.out.println(result);

    }
}

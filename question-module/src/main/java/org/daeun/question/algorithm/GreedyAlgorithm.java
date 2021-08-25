package org.daeun.question.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class GreedyAlgorithm {
    /*
    N개의 화학 물질 C1, C2, …, Cn이 있다.
    이들 각각은 보관되어야 할 온도가 각기 다른데, 각 Ci마다 최저 보관 온도 xi와 최고 보관 온도 yi가 정해져 있다.
    즉 Ci는 온도 xi이상, yi이하의 온도에서 보관되어야만 안전하다.
    이 화학 물질들을 모두 보관하기 위해서는 여러 대의 냉장고가 필요한데 가능하면 적은 수의 냉장고를 사용하고 싶다.
    이를 해결하는 프로그램을 작성하시오.


    첫줄에 화학물질의 수 N이 입력된다. N의 범위는 1이상 100 이하이다.
    두 번째 줄부터 N+1줄까지 최저보관온도와 최고보관온도가 입력된다.
    보관온도는 -270° ~ 10000°이며, 각 냉장고는 임의의 정해진 온도를 일정하게 유지할 수 있고, 냉장고는 아주 크다고 가정한다.

    첫줄에 최소로 필요한 냉장고의 대수를 출력한다.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Chemicals = sc.nextInt();
        //storageTemp[i][0] = 최저온도 storageTemp[i][1] = 최고온도
        int[][] storageTemp = new int[Chemicals][2];
        int refrigerator = 1;

        for (int i = 0; i < storageTemp.length; i++) {
                storageTemp[i][0] = sc.nextInt();
                storageTemp[i][1] = sc.nextInt();
        }

//        for (int i = 0; i < storageTemp.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.printf("C[%d][%d] = %d%n", i, j, storageTemp[i][j]);
//            }
//        }


        //for문으로 오름차순 정렬
        for (int i = 0; i < storageTemp.length; i++) {
            for (int j = i; j < storageTemp.length; j++) {
                if(storageTemp[j][1] < storageTemp[i][1]) {
                    int temp = storageTemp[i][1];
                    storageTemp[i][1] = storageTemp[j][1];
                    storageTemp[j][1] = temp;
                }
            }
        }

        //sort함수로 오름차순 정렬
//        Arrays.sort(storageTemp, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return Integer.compare(o1[1] , o2[1]);
//            }
//        });

        int maxTemp = storageTemp[0][1];

        for (int i = 1; i < Chemicals; i++) {
            if(maxTemp < storageTemp[i][0]) {
                maxTemp = storageTemp[i][1];
                refrigerator++;
            }
        }

        System.out.println(refrigerator);



    }
}

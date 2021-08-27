package org.daeun.question.greedyalgorithm;

import java.util.Scanner;

public class TutorialGreedy {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] weight = new int[5];
        weight[0] = 10;
        weight[1] = 10;
        weight[2] = 10;
        weight[3] = 0;
        weight[4] = 10;

/*        for (int i = 0; i < weight.length; i++) {
            weight[i] = sc.nextInt();
        }*/

//        int N = sc.nextInt();
        sc.close();
        int N = 89;

        int[] gram = {1, 2, 4, 8, 16};
//        int max = 0;
//
//        for (int i = 0; i < weight.length; i++) {
//            max += gram[i] * weight[i];
//        }
//
//        max -= N;

        int count = 0;

//        for (int i = weight.length - 1; i >= 0; i--) {
//            if (weight[i] <= N) {
//                count++;
//                N -= weight[i];
//            }
//        }

        int index = 4;
        while (index >= 0) {
            if (weight[index] == 0) {
                index--;
            }
            else if (gram[index] <= N) {
                N -= gram[index];
                weight[index]--;
                count++;
            } else {
                index--;
            }

        }

        System.out.println(count);
        for (int j : weight) {
            System.out.print(j + " ");
        }
    }
}

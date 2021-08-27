package org.daeun.question.greedyalgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TouringLecture {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numberOfUniversity = sc.nextInt();
        int[][] university = new int[numberOfUniversity][2];

//        int day[] = new int[numberOfUniversity];
//        int pay[] = new int[numberOfUniversity];


        for (int i = 0; i < numberOfUniversity; i++) {
            university[i][0] = sc.nextInt();
            university[i][1] = sc.nextInt();
        }

        for (int[] ints : university) {
            System.out.println(ints[0] + " " + ints[1]);
        }

        Arrays.sort(university, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

//        int index = 1;
        int day = university[0][1];
        int pay = university[0][0];
        int sum = 0;


        while (true) {
            for (int j = 1; j < university.length; j++) {
                if (day == university[j][1]) {
                    if (pay < university[j][0]) {
                        sum += pay;
                        day = university[j][1];
                        pay = university[j][0];
                    }
                } else {
                    day = university[j][1];
                    pay = university[j][0];
                    break;
                }
            }

        }


//        System.out.println(sum);
//        for (int[] ints : university) {
//            System.out.println(ints[0] + " " + ints[1]);
//        }

    }
}

package org.daeun.question.algorithm;

import java.util.Scanner;

public class BinarySearchAlgorithm {
    /*
    N개의 정렬된 데이터가 주어지고 Q개의 질의가 주어진다.
    정렬된 데이터가 목표값이 있는 위치를 찾는 프로그램을 작성하시오.

    첫 행에 N(100 <= N <= 500000)이 입력된다.
    두번째 행에 오름차순으로 정렬된 N개의 정수 a가 입력된다.
    세번째 행에 Q(100 <= N <= 500000)가 입력된다.
    네번째 행에 Q의 정수 b가 입력된다.

    Q개의 b에 대하여 각각의 결과를 공백으로 구분하여 하나의 행에 출력한다.
    배열의 첫번째 원소는 0번 , 마지막 원소는 N-1번에 저장된다고 가정한다.
    찾는 값이 없을 경우 -1일 출력한다.

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("N 배열 크기를 정해주세요 : ");
        int binaryArraySize = sc.nextInt();
        int[] binaryArray = new int[binaryArraySize];

        System.out.println("N 배열의 값을 정해주세요");
        for (int i = 0; i < binaryArray.length; i++) {
            int binaryArrayValue = sc.nextInt();
            binaryArray[i] = binaryArrayValue;
        }

        System.out.print("Q 배열 크기를 정해주세요 : ");
        int resultArraySize = sc.nextInt();
        int[] resultArray = new int[resultArraySize];


        for (int i = 0; i < resultArray.length; i++) {
            System.out.print("target 지정 : ");
            int target = sc.nextInt();

            int low = 1;
            int middle;
            int high = binaryArray.length - 1;


            while (low <= high) {

                middle = (low + high) / 2;

                if (binaryArray[middle] == target) {
                    resultArray[i] = middle;
                    break;
                }

                if (binaryArray[middle] > target) {
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            }
            if (low > high) {
                resultArray[i] = -1;
            }

        }

        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(resultArray[i] + " ");
        }


//        int[] binaryArray = {1,2,3,5,7,9,11,16};
//        int target = 10;
//        int low = 1;
//        int high = binaryArray.length-1;
//        int mid;
//        while (low <= high) {
//            mid = (low+high) / 2;
//            if(binaryArray[mid] == target) {
//                System.out.println("종료");
//                System.out.println(binaryArray[mid]);
//                break;
//            }
//            if(binaryArray[mid] > target) {
//                high = mid -1;
//            } else {
//                low = mid + 1;
//            }
//
//        }
//        System.out.print(-1 + " ");
    }
}

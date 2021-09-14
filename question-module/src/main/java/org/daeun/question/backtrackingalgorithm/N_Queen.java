package org.daeun.question.backtrackingalgorithm;

import java.util.Queue;
import java.util.Scanner;

public class N_Queen {
    private static int numberOfQueen;
    private static int[] chess;
    private static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numberOfQueen = sc.nextInt();
        chess = new int[numberOfQueen];
        backtrack(0);

        //queen의 수가 (1≤N≤13) 사이가 아니라면 다시 입력
        while (true) {
            if (numberOfQueen <= 0 || numberOfQueen >= 14) {
                System.out.print("Please re-enter : ");
                numberOfQueen = sc.nextInt();
            } else {
                break;
            }
        }

        System.out.println(count);
    }

    static void backtrack(int row) {
        //다 돌았다면 count
        if(row == numberOfQueen) {
            count++;
            return;
        }
        for (int i = 0; i < numberOfQueen; i++) {
            chess[row] = i;
            //row 행에 놓은 퀸의 자리가 괜찮다면
            if(isCheck(row)) {
                //다음 row 행으로
                backtrack(row + 1);
            }
        }
    }

    static boolean isCheck(int row) {
        for (int i = 0; i < row; i++) {
            //현재 row 위치에 퀸이 있음
            if (chess[i] == chess[row]) {
                return false;
            }
            //대각선 체크 (열의 차 == 행의 차 -> 대각선)
            if (Math.abs(row - i) == Math.abs(chess[i] - chess[row])) {
                return false;
            }
        }
        return true;
    }
}

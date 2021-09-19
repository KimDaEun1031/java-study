package org.daeun.question.backtrackingalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Sudoku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //9x9 판이 각각 3x3으로 나누어져 있음
        //비어있는 칸은 0으로 설정
        int[][] sudoku = new int[9][9];
        //체크 조건
        //빈 칸을 기준으로 가로, 세로, 정사각형 3x3을 감지해 체크
        //먼저 가로부터 체크

        //한 줄에는 1~9가 다 들어가 있어야함
        //맨 윗줄을 기준으로 체크
        //3x3이니까 0,1,2가 한팀 3,4,5가 한팀, 6,7,8이 한팀
        int[] check = new int[9];
        Random random = new Random();
        for (int index = 0; index < sudoku.length; index++) {
            //1~9 랜덤으로 집어 넣고 중복까지 체크 후에 중복 없으면 집어넣기
            for (int i = 0; i < sudoku.length; i++) {
                check[i] = random.nextInt(9) + 1;
                sudoku[index][i] = check[i];
                for (int j = 0; j < i; j++) {
                    if (check[i] == check[j]) {
                        i--;
                    }
                    sudoku[index][i] = check[i];
                }
            }
        }

        //

        System.out.println(Arrays.deepToString(sudoku));

//        System.out.println(Arrays.deepToString(sudoku));

    }
}

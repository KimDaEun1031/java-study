package org.daeun.question.recursionalgorithm;

import java.util.Scanner;

public class PutAStar10 {
    static char[][] arr;

    public static void main(String[] args) {
        //n은 무조근 3,9,27.. 3의 거듭제곱을 써야한다. 최소 3 최대 2187
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        arr = new char[N][N];

        rectangleStar(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void rectangleStar(int x, int y, int N, boolean blank) {

        // 공백칸일 경우
        if (blank) {
            for (int i = x; i < x + N; i++) {
                for (int j = y; j < y + N; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        // 더이상 쪼갤 수 없는 블록일 때
        if (N == 1) {
            arr[x][y] = '*';
            return;
        }

		/*
		   N=27 일 경우 한 블록의 사이즈는 9이고,
		   N=9 일 경우 한 블록의 사이즈는 3이듯
		   해당 블록의 한 칸을 담을 변수를 의미 size

		   count는 별 출력 누적을 의미
		 */

        int size = N / 3;
        int count = 0;
        for (int i = x; i < x + N; i += size) {
            for (int j = y; j < y + N; j += size) {
                count++;
                if (count == 5) { // 공백 칸일 경우
                    rectangleStar(i, j, size, true);
                } else {
                    rectangleStar(i, j, size, false);
                }
            }
        }
    }
}
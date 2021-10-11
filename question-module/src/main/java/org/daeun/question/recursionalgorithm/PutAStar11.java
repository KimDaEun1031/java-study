package org.daeun.question.recursionalgorithm;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class PutAStar11 {
    static char[][] arr;

    public static void main(String[] args) {
        //n은 3 x 2k / 3은 1, 3, 5
        //n 후보 3 6 12 24 48 ...
        //첫번째 두번째 세번째는 항상 1 3 5 근데 가운데니까 가운데부터 천천히 내려가자
        //n이 3일 땐 3x5 6일 땐 6x11 12일 땐 12x23 가로가 더해지네
        //그럼 판의 크기는 n x ((n x 2) - 1)

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        arr = new char[n][(n * 2) - 1];
        for (int i = 0; i < n; i++) {
            //초기화 안해주면 기본값 널문자 나옴
            Arrays.fill(arr[i], ' ');
        }

        triangleStar(0, n-1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * 2 - 1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void triangleStar(int x, int y, int n) {
        if (n == 3) {
            //첫번째  2
            //두번째 1, 3
            //세번째 0,1,2,3,4
            arr[x][y] = '*';
            arr[x+1][y-1] = arr[x+1][y+1] = '*';
            arr[x+2][y-2] = arr[x+2][y-1] = arr[x+2][y] = arr[x+2][y+1] = arr[x+2][y+2] = '*';
            return;
        }

        triangleStar(x, y, n/2);
        triangleStar(x+n/2, y-n/2, n/2);
        triangleStar(x+n/2, y+n/2, n/2);
    }
}
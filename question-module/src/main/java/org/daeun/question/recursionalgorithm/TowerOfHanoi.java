package org.daeun.question.recursionalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class TowerOfHanoi {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        BigInteger bi = new BigInteger("2").pow(N).subtract(BigInteger.ONE);
        sb.append(bi + "\n");

        if (N <= 20) {
            hanoi(N, 1, 2, 3);
        }

        System.out.println(sb);

    }

    public static void hanoi(int N, int start, int mid, int end) {
        if (N == 1) {
            sb.append(start + " " + end + '\n');
            return;
        }

        hanoi(N - 1, start, end, mid);

        sb.append(start + " " + end + '\n');

        hanoi(N - 1, mid, start, end);
    }
}

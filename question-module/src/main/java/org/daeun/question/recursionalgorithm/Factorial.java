package org.daeun.question.recursionalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int sum = factorial(n);
        System.out.println(sum);
    }

    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n-1);
        // 계승 공식 n * n-1 * n-2 * n-3 ...... * /
        // n = 5 || 5 * 4 * 3 * 2 * 1 = 120

    }
}

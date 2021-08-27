package org.daeun.question;

import java.util.Scanner;

public class OracleTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] coins = new int[6];
        for(int i = 0; i < 6; i++)
            coins[i] = in.nextInt();
        in.close();

//		int n = 212;
//		int[] coins = { 4, 2, 0, 4, 3, 4 };

        int[] values = { 500, 100, 50, 10, 5, 1 };
        int max = 0;
        for(int i = 0; i < 6; i++ ) {
            max += values[i] * coins[i];
        }
        max -= n;

        int index = 0;
        while(true) {

            if(max <= 0) {
                break;
            }

            if(coins[index] == 0 || max < values[index]) {
                index++;
                continue;
            }

            max -= values[index];
            coins[index]--;
        }

        int sum = 0;
        for(int coin : coins)
            sum += coin;

        System.out.println(sum);
        for(int coin : coins)
            System.out.printf("%d ", coin);
    }
}


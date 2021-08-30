package org.daeun.question.greedyalgorithm.vendingmachine;

import java.util.Scanner;

public class VendingMachineSolving_Blog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int itemPrice = sc.nextInt();
        int[] numberOfCoins = new int[6];
        for (int i = 0; i < numberOfCoins.length; i++) {
            numberOfCoins[i] = sc.nextInt();
        }

        int[] coins = {500, 100, 50, 10, 5, 1};
        int maxCoin = 0;

        for (int i = 0; i < coins.length; i++) {
            maxCoin += coins[i] * numberOfCoins[i];
        }

        maxCoin -= itemPrice;

        int index = 0;

        while (maxCoin > 0) {
            if (numberOfCoins[index] == 0 || maxCoin < coins[index]) {
                index++;
            } else {
                maxCoin -= coins[index];
                numberOfCoins[index]--;
            }
        }

        int sum = 0;
        for (int numberOfCoin : numberOfCoins) {
            sum += numberOfCoin;
        }

        System.out.println(sum);
        for (int coin : numberOfCoins) {
            System.out.print(coin + " ");
        }


    }
}

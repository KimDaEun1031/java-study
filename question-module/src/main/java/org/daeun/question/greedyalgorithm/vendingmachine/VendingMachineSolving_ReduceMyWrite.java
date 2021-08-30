package org.daeun.question.greedyalgorithm.vendingmachine;

import java.util.Scanner;

public class VendingMachineSolving_ReduceMyWrite {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int itemPrice = 407;
        int[] numberOfCoins = {0, 4, 5, 2, 7, 3, 4};

//        for (int i = 0; i < numberOfCoins.length; i++) {
//            numberOfCoins[i] = sc.nextInt();
//        }

        sc.close();

        int[] coins = {1000, 500, 100, 50, 10, 5, 1};

        for (int i = numberOfUnusedCoins(1, coins.length); i >= 0; i--) {
            int checkSave = 0;
            for (int j = 1; j <= numberOfCoins[i]; j++) {
                itemPrice = numberOfUnusedCoins(coins[i], itemPrice);
                if(itemPrice % coins[numberOfUnusedCoins(1, i)] == 0) {
                    checkSave = j;
                }

                if(itemPrice <= 0) {
                    saveCheckNumberInCoinsArray(numberOfCoins, i, checkSave);
                    break;
                }
            }

            if (numberOfUnusedCoins(checkSave, numberOfCoins[i]) != 0) {
                itemPrice += coins[i] * numberOfUnusedCoins(checkSave, numberOfCoins[i]);
                saveCheckNumberInCoinsArray(numberOfCoins, i, checkSave);
            }
        }

        for (int i = 0; i < coins.length; i++) {
            System.out.print(numberOfCoins[i] + " ");
        }
    }

    private static int numberOfUnusedCoins(int checkSave, int numberOfCoin) {
        return numberOfCoin - checkSave;
    }

    private static void saveCheckNumberInCoinsArray(int[] numberOfCoins, int i, int checkSave) {
        numberOfCoins[i] = checkSave;
    }
}

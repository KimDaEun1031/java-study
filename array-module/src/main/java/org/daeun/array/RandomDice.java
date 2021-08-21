package org.daeun.array;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomDice {
    public static void main(String[] args) {

        int[] arrayDiceNumber = new int[6];
        int[] arrayCheckDice = new int[6];

        for (int i = 0; i < 6; i++) {
            arrayCheckDice[i] = 0;
            arrayDiceNumber[i] = i+1;
        }

        for (int i = 0; i < 10; i++) {
            //랜덤으로 주사위 10개 생성
            int diceRandom = (int) ((Math.random() * 6) + 1);
            log.info("diceRandom = {}",diceRandom);

            for (int j = 0; j < arrayCheckDice.length; j++) {
                if(arrayDiceNumber[j]==diceRandom) {
                    arrayCheckDice[j]++;
                }
            }

        }

        for (int i = 1; i <= arrayCheckDice.length; i++) {
            log.info("dice["+ i +"] = " +arrayCheckDice[i-1]);
        }


    }
}

package org.daeun.question.dfsalgorithm;

public class TutorialForeach {
    public static void main(String[] args) {
        char[] chars = new char[26];
        char a = 65;

        for (int i = 0; i < chars.length; i++) {
            chars[i] = a++;
        }

        for (char count : chars) {
            System.out.println(count);
        }
    }
}

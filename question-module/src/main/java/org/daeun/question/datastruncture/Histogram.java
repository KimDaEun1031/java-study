package org.daeun.question.datastruncture;

import java.util.Scanner;
import java.util.Stack;

public class Histogram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int stackLength = sc.nextInt();

        int stackNumber[] = new int[stackLength];

        for (int i = 0; i < stackLength; i++) {
            stackNumber[i] = sc.nextInt();
        }

        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < stackNumber.length; i++) {
            //스택이 비어있지 않고 스택의 가장 첫번째 값(넣어진 i값)의 배열 값이 배열의 i번째의 값보다 크거나 같다면 true
            //stack.peek()는 현 i보다 항상 낮은 값이다.
            while (!stack.isEmpty() && stackNumber[stack.peek()] >= stackNumber[i]) {
                //스택의 제일 첫번째 값의 배열 값
                int height = stackNumber[stack.pop()];
                //스택이 비었다면 현재 배열의 번호를 너비로 설정
                //안 비었다면 현재 배열 번호에서 1을 뺀 후에 스택의 가장 첫번째 값을 뺀다.
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();

                maxArea = Math.max(maxArea, height * width); //최댓값 구하는 함수

            }
            stack.push(i);
        }

        System.out.println(maxArea);

    }
}

package org.daeun.question.kakao;

public class StringCompression {
    public static void main(String[] args) {
        //s의 길이 / 2에 따라 for문 조건 걸기
        //1~s의 길이 / 2까지 나눴는데 솔직히 길이까지 간다면 이미 못나눈거나 다름이 없으니 가장 짧은 길이는 현재 길이
        String s = "aabbaccc";

        int min = 0;

        String[] strArray = s.split(""); //한글자씩
        for (int i = 0; i < s.length() / 2; i++) {
            for (int j = i + 1; j < i; j++) {

            }
        }

    }
}

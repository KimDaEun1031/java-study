package org.daeun.question.dcalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberCard {
    private static int[] cardPack;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //상대방이 가지고 있는 숫자 카드의 개수
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        //상대방이 가지고 있는 숫자 카드들
        cardPack = new int[N];

        //상대방이 가지고 있는 숫자 카드 개별
        for (int i = 0; i < cardPack.length; i++) {
            cardPack[i] = Integer.parseInt(st.nextToken());
        }

        //이분 탐색시 배열은 정렬
        Arrays.sort(cardPack);

        //내가 물어볼 질문 개수
        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            //물어본 숫자 카드
            int searchCard = Integer.parseInt(st.nextToken());
            //숫자카드 검색
            sb.append(binarySearch(cardPack, N, searchCard));
        }

        System.out.println(sb + "\n");
    }

    //있다면 질문 위치에 1로 답을 표시 없다면 0으로 표시
    public static int binarySearch(int[] cards, int N, int search) {
        int first = 0;
        int last = N - 1;
        int mid;

        while (first <= last) {
            mid = (first + last) / 2;

            //맞췄다면 1을 반환
            if (cards[mid] == search) {
                return 1;
            }

            //찾지 못했고 찾으려는 숫자가 중간값보다 작다면 중간값 위
            if (cards[mid] < search) {
                first = mid + 1;
            }
            //작지 않다면 중간값 아래
            else {
                last = mid - 1;
            }
        }

        //맞추지 못했다면 0을 반환
        return 0;
    }
}

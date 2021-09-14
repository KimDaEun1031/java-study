package org.daeun.question.kakao;

import java.util.Arrays;

public class Question01 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] id_list = new String[4];
        id_list[0] = "muzi";
        id_list[1] = "frodo";
        id_list[2] = "apeach";
        id_list[3] = "neo";

        String[] report = new String[5];
        report[0] = "muzi frodo";
        report[1] = "muzi frodo";
        report[2] = "frodo neo";
        report[3] = "muzi neo";
        report[4] = "apeach muzi";

        int k = 3;

        System.out.println(Arrays.toString(solution.solution(id_list, report, k)));


    }
}

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        int[] report_check = new int[id_list.length];
        int[] id_check = new int[id_list.length];

        int index = 0;
        int[] temp = new int[id_check.length];


        for (int i = 0; i < report_check.length; i++) {
            String[] id = report[i].split(" ");

            if (id_list[i].equals(id[1])) {
                id_check[i] += 0;
            }

            for (int j = 0; j < report_check.length; j++) {
                if (id_list[j].equals(id[0])) {

                    for (int l = 0; l < report_check.length; l++) {
                        if (id_list[l].equals(id[1])) {
                            report_check[l]++;
                            if (report[i].equals(report[i + 1])) {
                                report_check[l]--;
                                id_check[i]--;
                            }

                            if (report_check[l] == k) {
                                id_check[j]++;

                                for (int m = 0; m < index; m++) {
                                    id_check[temp[m]]++;
                                }

                                index = 0;
                                temp = new int[id_check.length];

                            } else {
                                temp[index] = j;
                                index++;
                            }
                        }
                    }

                }
            }


        }


        System.out.println(id_check.length);

        return id_check;
    }

}

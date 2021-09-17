package variousalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class VotingForTheStudentPresident {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfStudent = Integer.parseInt(br.readLine());

        while (true) {
            if (numberOfStudent <= 3 || numberOfStudent >= 1000) {
                System.out.print("Please re-enter : ");
                numberOfStudent = Integer.parseInt(br.readLine());
            } else {
                break;
            }
        }

        int[][] student = new int[numberOfStudent][3];
        int count[] = new int[3];
        int check;

        //투표
        voteStudentPresident(br, student, count);

        int[] calculatingTheScore = new int[3];

        //각 후보 당 1, 2, 3점 횟수 저장
        for (int i = 0; i < student.length; i++) {
            for (int j = 0; j < student.length-1; j++) {
                if (student[i][j] == 1) {
                    calculatingTheScore[0]++;
                } else if (student[i][j] == 2) {
                    calculatingTheScore[1]++;
                } else {
                    calculatingTheScore[2]++;
                }
            }
        }

        //비교하기 - 1, 2, 3이 각각 하나씩 들어갔는지
        for (int i = 0; i < student.length; i++) {
            for (int j = 0; j < count.length-1; j++) { //0 1 2
                //0번 0번 == 0번 1번 / 0번 1번 == 0번 2번 / 0번 0번 == 2번
                if (student[i][j] == student[i][j+1]) {
                    voteStudentPresident(br, student, count);
                }
                if (student[i][1] == student[i][2]) {
                    voteStudentPresident(br, student, count);
                }
            }
        }

        //비교하기 - 1번 후보 2번 후보 3번 후보 최종 점수 비교하기

        System.out.println(Arrays.toString(count));




    }

    private static void voteStudentPresident(BufferedReader br, int[][] student, int[] count) throws IOException {
        int check;
        for (int i = 0; i < student.length; i++) {
            for (int j = 0; j < student.length - 1; j++) {
                student[i][j] = Integer.parseInt(br.readLine());
                check = student[i][j];
                count[j] += student[i][j];
            }
        }
    }
}

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
        int[] score = new int[3];
        int[][] calculatingTheScore = new int[3][3];

        voteStudentPresident(br, student, score, calculatingTheScore);

        //투표
        System.out.println(Arrays.deepToString(student));

        //비교하기 - 1, 2, 3이 각각 하나씩 들어갔는지
        checkNumberOfVote(br, student, score, calculatingTheScore);
        int[] highScore = new int[2];

        //비교하기 - 1번 후보 2번 후보 3번 후보 최종 점수 비교하기
        getHighScore(score, calculatingTheScore, highScore);
        if (highScore[1] > 0) {
            //최종점수가 같고 2, 3점의 개수도 같다면
            int noneVote = 0;
            System.out.printf("결정할 수 없습니다. 후보수 : %d, 최대득표수 : %d", noneVote, score[highScore[0]]);
        } else {
            System.out.printf("회장으로 뽑힌 학생 후보 번호 : %d, 최대득표수 : %d", highScore[0]+1, score[highScore[0]]);
        }
    }

    private static void checkNumberOfVote(BufferedReader br, int[][] student, int[] score, int[][] calculatingTheScore) throws IOException {
        for (int[] ints : student) {
            for (int j = 0; j < score.length - 1; j++) { //0 1 2
                //0번 0번 == 0번 1번 / 0번 1번 == 0번 2번 / 0번 0번 == 2번
                if (ints[j] == ints[j + 1]) {
                    voteStudentPresident(br, student, score, calculatingTheScore);
                }
                if (ints[1] == ints[2]) {
                    voteStudentPresident(br, student, score, calculatingTheScore);
                }
            }
        }
    }

    private static void voteStudentPresident(BufferedReader br, int[][] student, int[] score, int[][] calculatingTheScore) throws IOException {
        for (int i = 0; i < student.length; i++) {
            for (int j = 0; j < score.length; j++) {
                student[i][j] = Integer.parseInt(br.readLine());
                //후보 당 최종 점수 계산
                score[j] += student[i][j];
                //후보 당 1, 2, 3점 횟수 계산
                if (j == 0) {
                    calScore(student, calculatingTheScore, i, j);
                }
                if (j == 1) {
                    calScore(student, calculatingTheScore, i, j);
                }
                if (j == 2) {
                    calScore(student, calculatingTheScore, i, j);
                }
            }
        }
    }

    //각 1, 2, 3점 점수 횟수 계산
    private static void calScore(int[][] student, int[][] calculatingTheScore, int i, int j) {
        if (student[i][j] == 1) {
            calculatingTheScore[j][0]++;
        } else if (student[i][j] == 2) {
            calculatingTheScore[j][1]++;
        } else {
            calculatingTheScore[j][2]++;
        }
    }

    private static void getHighScore(int[] score, int[][] calculatingTheScore, int[] highScore) {
        for (int i = 0; i < score.length - 1; i++) {
            //0번 1번 / 1번 2번 비교 혹은 0번 2번 비교 했을 때 같은거가 있는데 만약 같은거보다 더 높은게 있다면 저장
            //없다면 개수 비교 들어가기
            if (score[i] == score[i + 1]) {
                //0번 1번이면 2번 / 1번 2번이면 0번
                int remainder = score.length - (i + (i + 1));
                // 오른쪽 후보의 점수가 더 높다면
                if (score[i] < score[remainder]) {
                    highScore[0] = remainder;
                }
                //0번 1번 같
                getCompareScoreCount(calculatingTheScore, highScore, i);
            }
        }

        if (score[0] == score[2]) {
            if (score[0] < score[1]) {
                highScore[0] = 1;
            }
            if (calculatingTheScore[0][2] > calculatingTheScore[2][2]) { //3점 개수 비교
                highScore[0] = 0;
            }
            if (calculatingTheScore[0][2] < calculatingTheScore[2][2]) {
                highScore[0] = 2;
            }
            if (calculatingTheScore[0][2] == calculatingTheScore[2][2] && calculatingTheScore[0][1] > calculatingTheScore[2][1]) { //2점 개수 비교
                highScore[0] = 0;
            }
            if (calculatingTheScore[0][2] == calculatingTheScore[2][2] && calculatingTheScore[0][1] < calculatingTheScore[2][1]) {
                highScore[0] = 2;
            } else {
                highScore[0] = 0;
                highScore[1] = 0;
            }
        }
    }

    private static void getCompareScoreCount(int[][] calculatingTheScore, int[] highScore, int i) {
        if (calculatingTheScore[i][2] > calculatingTheScore[i + 1][2]) { //3점 개수 비교
            highScore[0] = i;
        } else if (calculatingTheScore[i][2] < calculatingTheScore[i + 1][2]) {
            highScore[0] = i + 1;
        } else if (calculatingTheScore[i][2] == calculatingTheScore[i + 1][2] && calculatingTheScore[i][1] > calculatingTheScore[i + 1][1]) { //2점 개수 비교
            highScore[0] = i;
        } else if (calculatingTheScore[i][2] == calculatingTheScore[i + 1][2] && calculatingTheScore[i][1] < calculatingTheScore[i + 1][1]) {
            highScore[0] = i + 1;
        } else {
            highScore[0] = 0;
            highScore[1] = 0;
        }
    }
}

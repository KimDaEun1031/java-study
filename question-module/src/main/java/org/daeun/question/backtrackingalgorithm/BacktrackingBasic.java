package org.daeun.question.backtrackingalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BacktrackingBasic {
    public static int size;
    public static int[][] chess;

    //y와 x 값을 더하고 빼는 걸로
    public static int[] dy = {-1, -1, 1, 1};
    public static int[] dx = {-1, 1, -1, 1};

    public static int b_cnt = 0;
    public static int w_cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        size = Integer.parseInt(br.readLine());

        //요령 1 - 배열을 1부터 시작함으로써 끝을 체크하지 않음
        // 0부터 시작했다면 항상 0인지 체크를 해줘야하는데 그것을 비껴감
        chess = new int[size + 1][size + 1];

        for (int i = 1; i <= size; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= size; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] black_visited = new boolean[size + 1][size + 1];

        black_search(black_visited, 1, 1, 0);


    }

    private static void black_search(boolean[][] visited, int x, int y, int cnt) {

        b_cnt = Math.max(cnt, b_cnt);

        if (x < size) {
            y += 1;
            x = (y % 2 == 0) ? 2 : 1;
        }

        if (y > size) return;

        //백트래킹 재귀함수쓸 땐 디버깅하고 스택 항상 확인
        //값이 들어오지 않았다면 값이 들어왔던 스택 부분으로 한번에 감

        if (isAble(visited, x, y)) {
            visited[y][x] = true;
            black_search(visited, x + 2, y, cnt + 1);
            visited[y][x] = false;
        }
        black_search(visited, x + 2, y, cnt);
    }

    private static boolean isAble(boolean[][] visited, int x, int y) {
        if (chess[y][x] == 0) return false;

        for (int i = 0; i < 4; i++) {
            int yy = dy[i] + y;
            int xx = dx[i] + x;

            for (int j = 1; j <= size; j++) {
                if (yy > 0 && x > 0 && yy <= size && xx <= size) {
                    if (visited[yy][xx]) return false;

                    yy += dy[i];
                    xx += dx[i];
                }
            }
        }
        return true;
    }
}

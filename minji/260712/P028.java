import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0,0); // (좌표, 색종이 사용 개수)

        if(answer == Integer.MAX_VALUE) {
            System.out.println("-1");
        }
        else {
            System.out.println(answer);
        }
    }

    private static void backtracking(int xy, int useCnt) {
        // 정답 처리 (모든 칸을 다 확인한 경우)
        if(xy == 100) {
            answer = Math.min(answer, useCnt);
            return;
        }

        // 가지치기
        if(answer <= useCnt) return;

        int x = xy % 10;
        int y = xy / 10;

        // 현재 칸이 1이면 색종이를 붙여야 함
        if(board[y][x] == 1) {
            for(int i=5; i>0; i--) {
                if(papers[i] > 0 && check(x, y, i)) {
                    papers[i]--;                // 색종이 사용
                    fill(x, y, i, 0);       // 붙인 영역 0으로 변경

                    backtracking(xy+1, useCnt+1);

                    fill(x, y, i, 1);       // 원상복구
                    papers[i]++;                // 색종이 개수 복구
                }
            }
        }
        // 현재 칸이 0이면 그냥 다음 칸으로 이동
        else {
            backtracking(xy+1, useCnt);
        }
    }

    private static void fill(int x, int y, int size, int num) {
        for(int i=y; i<y+size; i++) {
            for(int j=x; j<x+size; j++) {
                board[i][j] = num;
            }
        }
    }

    private static boolean check(int x, int y, int size) {
        if(x+size > 10 || y+size > 10) return false;

        for(int i=y; i<y+size; i++) {
            for(int j=x; j<x+size; j++) {
                if(board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
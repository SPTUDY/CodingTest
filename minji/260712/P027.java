import java.util.*;

public class Main {
    public static int N;
    public static int[] board;  // board[행] = 열
                                // board[a] = b 는 "a행 b열에 퀸을 놓는다"는 의미
    public static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N];
        backtracking(0);
        System.out.println(answer);
    }

    /**
     * currentRow = 지금 퀸을 놓으려는 행
     * col = 현재 행에서 시도해보는 열
     * prevRow = 이전에 이미 퀸을 놓았던 행
     * board[row] = row행에 놓인 퀸의 열 위치
     */

    private static void backtracking(int currentRow) {
        // 모든 행에 퀸을 하나씩 놓았다면 경우의 수 1개 완성
        if(currentRow == N) {
            answer++;
            return;
        }

        // currentRow 행에서 0 ~ N-1번 열까지 퀸을 놓아본다
        for(int col=0; col<N; col++) {
            board[currentRow] = col;

            // 현재 위치에 퀸을 놓아도 안전하면 다음 행으로 이동
            if(check(currentRow)) {
                backtracking(currentRow+1);
            }
        }
    }

    private static boolean check(int currentRow) {
        for(int prevRow=0; prevRow<currentRow; prevRow++) {
            // 같은 열에 퀸이 있으면 공격 불가능 (일직선 공격)
            if(board[prevRow] == board[currentRow]) {
                return false;
            }

            // 행 차이와 열 차이가 같으면 같은 대각선이므로 불가능
            if(Math.abs(prevRow-currentRow) == Math.abs(board[prevRow]-board[currentRow])) {
                return false;
            }
        }
        return true;
    }
}
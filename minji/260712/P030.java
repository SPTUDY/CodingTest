import java.io.*;
import java.util.*;

public class Main {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    public static int[][] board;
    public static boolean[][] visited;
    public static int N, M;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        board = new int[N][M];
        for(int i=0; i<N; i++) {
            String s = sc.next();
            for(int j=0; j<M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }
        visited = new boolean[N][M];

        bfs(0, 0);
        System.out.println(board[N-1][M-1]);
    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i,j});
        visited[i][j] = true;

        while(!q.isEmpty()) {
            int now[] = q.poll();
            for(int k=0; k<4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if(x<0 || x>=N || y<0 || y>=M) {
                    continue;
                }

                if(board[x][y] == 1 && !visited[x][y]) {
                    visited[x][y] = true;
                    board[x][y] = board[now[0]][now[1]] + 1; // 깊이 업데이트
                    q.add(new int[] {x,y});
                }
            }
        }
    }
}
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        visited = new boolean[N+1];
        arr = new int[M];

        backtracking(0);
        System.out.println(sb);
    }

    private static void backtracking(int depth) {
        if(depth == M) {
            for(int i=0; i<M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 1~N 중에 숫자 하나 선택
        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = i; // 현재 위치에 숫자 저장

                backtracking(depth+1);

                visited[i] = false;
            }
        }
    }
}
import java.util.*;

public class Main {
    static int[][] D;
    static int N = 14;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        D = new int[N+1][N+1]; // [층][호]

        // 초기값
        for(int i=0; i<=N; i++) {
            D[0][i] = i; // 0층
            D[i][1] = 1; // 각 층의 1호
        }

        // DP
        for(int i=1; i<=N; i++) { // 1~N층
            for(int j=2; j<=N; j++) { // 2~N호
                D[i][j] = D[i-1][j] + D[i][j-1];
            }
        }

        // 질의 수행
        for(int t=0; t<T; t++) {
            int floor = sc.nextInt();
            int room = sc.nextInt();
            System.out.println(D[floor][room]);
        }
    }
}
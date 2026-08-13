import java.util.*;

public class Main {
    static long[][] D;

    public static void main(String[] args) {
        D = new long[31][31]; // [M][N] : M개 중에 N개의 조합수

        // 초기값
        for(int i=0; i<=30; i++) {
            D[i][0] = 1;
            D[i][i] = 1;
            D[i][1] = i;
        }

        // DP
        for(int i=2; i<=30; i++) {
            for(int j=1; j<i; j++) {
                D[i][j] = D[i-1][j-1] + D[i-1][j];
            }
        }

        // 질의
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t=0; t<T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            System.out.println(D[M][N]);
        }
    }
}
import java.util.*;

public class Main {
    static int N;
    static int[] D, T, P;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        D = new int[N+2]; // i번째 날부터 퇴사일(N+1)까지 벌 수 있는 최대 수입
        T = new int[N+1];
        P = new int[N+1];

        for(int i=1; i<=N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        for(int i=N; i>0; i--) {
            if(i + T[i] > N+1) {
                D[i] = D[i+1];
            }
            else {
                D[i] = Math.max(D[i+1], D[i + T[i]] + P[i]);
            }
        }

        System.out.println(D[1]);
    }
}
import java.util.*;

public class Main {
    static int N;
    static long[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = new long[N+1]; // 길이가 i인 이친수의 개수

        D[1] = 1;
        D[2] = 1;
        for(int i=3; i<=N; i++) {
            D[i] = D[i-1] + D[i-2]; // D[i-1] : 마지막이 0인 경우의 수
                                    // D[i-2] : 마지막이 01인 경우의 수
        }

        System.out.println(D[N]);
    }
}
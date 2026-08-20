import java.util.*;

public class Main {
    static int N;
    static int[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = new int[N+1]; // 2*i 크기를 채우는 경우의 수

        D[1] = 1;
        D[2] = 2;

        for(int i=3; i<=N; i++) {
            D[i] = (D[i-1] + D[i-2]) % 10_007; // D[i-1] : 세로 1개로 끝나는 경우의 수
                                               // D[i-2] : 가로 2개로 끝나는 경우의 수
        }

        System.out.println(D[N]);
    }
}
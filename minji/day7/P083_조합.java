import java.util.*;

public class Main {
    static int M, K;
    static int[] stones;
    static double answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        stones = new int[M];
        int total = 0;

        for(int i=0; i<M; i++) {
            stones[i] = sc.nextInt();
            total += stones[i];
        }

        K = sc.nextInt();

        for(int i=0; i<M; i++) {
            if(stones[i] < K) {
                continue;
            }

            double probability = 1.0;
            for(int j=0; j<K; j++) {
                probability *= (double) (stones[i] - j) / (total - j);
            }
            answer += probability;
        }

        System.out.println(answer);
    }
}
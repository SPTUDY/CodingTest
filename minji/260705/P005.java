import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int mod = sc.nextInt();

        int[] arr = new int[n+1];
        int[] s = new int[n+1];
        int[] m = new int[n+1];

        for(int i=1; i<=n; i++) {
            arr[i] = sc.nextInt();
            s[i] = s[i-1] + arr[i];
            m[i] = s[i] % mod;
        }

        long[] remainder = new long[mod]; // 0 ~ n-1 까지 나머지 구하기
        for(int check : m) {
            remainder[check]++;
        }

        int answer = 0;
        for(int i=0; i<mod; i++) {
            if(remainder[i] > 1) {
                // ex. 5C2 = (5 * 4) / 2
                answer += remainder[i] * (remainder[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}

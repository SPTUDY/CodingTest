import java.util.*;

public class P005 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[] s = new long[n];
        long[] c = new long[m]; // 나머지 개수 담을 배열
        long answer = 0;

        // 합배열
        s[0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + sc.nextInt();
        }

        // 합배열에 나머지 연산
        for (int i = 0; i < n; i++) {
            int rem = (int) s[i] % m;
            if (rem == 0) answer++;
            // 나머지 개수 계산
            c[rem]++;
        }

        for (int i = 0; i < m; i++) {
            // 같은 나머지를 가진 합배열이 2개 이상인 경우
            if (c[i] > 1) {
                // 조합 계산 (r*(r-1)/2)
                answer = answer + (c[i] * (c[i] - 1) / 2);
            }
        }
        System.out.println(answer);

    }
}
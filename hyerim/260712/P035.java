import java.io.*;
import java.util.*;

public class P035 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];

        //동전이 서로 배수라는 조건이 주어지기 때문에 최적해 보장
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;

        //동전이 오름차순으로 입력되기 때문에
        //배열의 맨 뒤부터 확인해야 그리디 알고리즘 사용 가능
        //큰 동전 먼저 사용
        for (int i = n - 1; i >= 0; i--) {
            if (coin[i] <= k) {
                answer += k / coin[i];
                k %= coin[i];
            }
        }

        System.out.println(answer);
    }
}
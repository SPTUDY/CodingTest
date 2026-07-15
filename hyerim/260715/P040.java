import java.io.*;
import java.util.*;

public class P040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        //처음에는 모두 소수라고 가정
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        //0과 1은 소수가 아님
        prime[0] = false;
        prime[1] = false;

        //제곱근을 기준으로 짝이 생김
        //n = i*i일때, n이하의 숫자들은 반드시 i 이하의 약수를 가짐
        for (int i = 2; i * i <= n; i++) {
            //만약 소수라면 해당 수의 배수를 모두 제거 (false 처리)
            if (prime[i]) {
                //i*i부터 시작하는 이유는
                //i*(i보다 작은 값)은 이미 i보다 작은 값의 배수를 지우면서 지워짐
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }

        for (int i = m; i <= n; i++) {
            if (prime[i]) System.out.println(i);
        }
    }
}
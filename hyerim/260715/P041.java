import java.io.*;
import java.util.*;

public class P041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        //b가 최댓값이기 때문에
        //소수의 가능한 최댓값은 b의 제곱근값
        int max = (int) Math.sqrt(b);

        //처음에는 모두 소수라고 가정
        boolean[] prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        //0과 1은 소수가 아님
        prime[0] = false;
        prime[1] = false;

        //max 제곱근 이하의 소수를 에라토스테네스의 체로 구함
        for (int i = 2; i * i <= max; i++) {
            //만약 소수라면 해당 수의 배수를 모두 제거 (false 처리)
            if (prime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    prime[j] = false;
                }
            }
        }
        int answer = 0;

        for (int i = 2; i <= max; i++) {
            if (prime[i]) {
                //거의 소수는 i^2부터 시작
                long num = (long) i * i;
                //num이 b 이하일 동안
                while (num <= b) {
                    //num이 a 이상이면 answer++
                    if (num >= a) answer++;
                    //num * i > b이면 더이상 확인할 필요 없음
                    //곱셈 오버플로우 방지를 위해 나눗셈으로 검사
                    if (num > b / i) break;
                    //만약 그냥 넘어가면 i를 한번 더 곱함
                    num *= i;
                }
            }
        }
        System.out.println(answer);


    }
}
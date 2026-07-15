import java.io.*;

public class P044 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        long result = n;

        for (long i = 2; i * i <= n; i++) {
            //i가 n의 약수라면, 즉 소인수라면 해당 소인수의 배수 제거
            if (n % i == 0) {
                result = result - result / i;
                //같은 소인수를 두번 갖고 있는 경우 한번만 처리
                //예를 들어 n=12, i=2인 경우
                //2를 약수로 갖지 않을때까지 n = n/i 실행
                while (n % i == 0) n /= i;
            }
        }
        //반복문이 끝났는데 n이 1보다 크면 그 값도 소인수
        //예를 들어 n=17이면 위에 반복문에 안걸리므로 값이 안바뀜
        //17-17/17 = 16, 즉 17은 1~16이 모두 서로소이므로 16개가 정답이 됨
        if (n > 1) result = result - result / n;
        System.out.println(result);
    }
}
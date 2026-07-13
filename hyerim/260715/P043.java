import java.io.*;
import java.util.*;

public class P043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        //max값이 매우 크기 때문에 1부터 배열을 만들면 안됨
        boolean[] check = new boolean[(int) (max - min + 1)];

        //max의 제곱근까지만 확인
        //i^2가 max보다 크면 해당 제곱수로 떨어지는 범위 내부의 수가 없기 때문
        for (long i = 2; i * i <= max; i++) {
            long pow = i * i;   //현재 제곱수
            //min 이상에서 제곱수의 첫번째 배수 찾기
            long start = min / pow;
            //나누어 떨어지지 않으면 다음 배수에서 시작될 수 있도록 함
            //예를 들어 min = 10, pow = 4일 때, 아래 조건 만족
            //start(2)에서 ++(3)
            //아래의 for문에서 3~4*3 (만약 start++ 없으면 2~4*2라서 min조건 만족 못함)
            if (min % pow != 0) start++;

            //제곱수의 배수 지우기
            //j는 pow의 배수 번호
            //j에 제곱수를 곱하면서 max보다 작거나 같을 때까지 지우기
            for (long j = start; pow * j <= max; j++) {
                //min~max 배열을 만들었지만 인덱스는 0~max-min
                check[(int) ((j * pow) - min)] = true;
            }
        }

        int count = 0;
        //false인 부분은 제곱수로 나누어 떨어지지 않는 수
        for (int i = 0; i <= max - min; i++) {
            if (!check[i]) count++;
        }
        System.out.println(count);

    }
}
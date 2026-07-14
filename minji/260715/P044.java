import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long answer = N; // 1~N 모든 수가 소인수라고 가정

        for(long p=2; p<=Math.sqrt(N); p++) {

            // N이 p로 나누어떨어지면 p는 N의 소인수임
            if(N%p == 0) {
                // 소인수 p의 배수는 N과 서로소가 될 수 없으므로 지웅
                answer = answer - answer/p;
                while(N%p == 0) {
                    N /= p;
                }
            }
        }

        // 아직 소인수 구성이 남아있을 때
        if(N > 1) {
            answer -= answer/N;
        }

        System.out.println(answer);
    }
}
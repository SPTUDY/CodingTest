import java.io.*;
import java.util.*;

public class P024 {
    static int n;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        //왼쪽부터 한자리, 두자리, ..., n자리 모두 소수인 n의 자릿수 구하기
        //제일 왼쪽 수는 소수이어야 함
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    static void DFS(int num, int j) {
        //자릿수 j가 만족되면
        if (j == n) {
            //소수인지 확인 후 출력 및 종료
            if (isPrime(num)) System.out.println(num);
            return;
        }
        for (int i = 1; i < 10; i++) {
            //짝수는 넘어감
            if (i % 2 == 0) continue;
            //1, 3, 5, 7, 9를 일의 자릿수에 더해가며 DFS 수행
            //일의 자릿수에 더할 때, 자릿수 +1
            if (isPrime(num * 10 + i)) DFS(num * 10 + i, j + 1);

        }
    }

    //소수인지 확인하는 함수
    //절반까지만 확인해도 소수인지 알 수 있음
    //만약 소수가 아니면 false 리턴
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
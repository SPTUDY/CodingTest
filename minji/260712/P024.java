import java.io.*;
import java.util.*;

public class Main {
    public static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 한자리 소수: 2, 3, 5, 7
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    private static void DFS(int num, int jarisu) {
        if(jarisu == N) {
            if(isPrime(num)) {
                System.out.println(num);
            }
            return;
        }

        for(int i=1; i<10; i+=2) {
            int checkNum = num*10 + i;
            if(isPrime(checkNum)) {
                DFS(checkNum, jarisu+1);
            }
        }
    }

    private static boolean isPrime(int n) {
        // 1이면 소수 아님
        if(n<2) return false;

        // 소수 판별은 sqrt(n)까지만 해도 ㄱㅊ
        for(int i=2; i*i <= n; i++) {
            if(n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
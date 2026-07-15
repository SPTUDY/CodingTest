import java.io.*;
import java.util.*;

public class P045 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = a * b / gcd(a, b);
            System.out.println(result);
        }
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;   //나머지가 0이면 작은 수 리턴 후 종료
        else return gcd(b, a % b);
    }
}
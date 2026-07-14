import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        long gcd = gcd(a,b);

        if(c%gcd != 0) {
            System.out.println("-1");
        }
        else {
            int mok = (int) (c / gcd);
            long[] answer = euclide(a, b);
            System.out.println(answer[0] * mok + " " + answer[1] * mok);
        }
    }

    public static long[] euclide(long a, long b) {
        long[] ans = new long[2];
        if(b==0) {
            ans[0] = 1;
            ans[1] = 0;
            return ans;
        }

        long q = a/b;
        long[] v = euclide(b, a%b);
        ans[0] = v[1];
        ans[1] = v[0] - v[1] * q;
        return ans;
    }

    public static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        else {
            return gcd(b, a%b);
        }
    }
}
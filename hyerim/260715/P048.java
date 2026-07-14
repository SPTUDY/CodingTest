import java.io.*;
import java.util.*;

public class P048 {
    static long x;
    static long y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        long gcd = extendedGDC(a, b);

        //c가 gcd(a, b)로 나누어떨어지지 않으면 정수해가 없음
        if (c % gcd != 0) {
            System.out.println(-1);
        } else {
            //구해진 x, y는 ax+by=gcd(a,b)를 만족하는 값
            //구하고자 하는 값은 ax+by=c이므로 양변에 c/gcd를 곱하면 됨
            long multiply = c / gcd;
            System.out.println((x * multiply) + " " + (y * multiply));
        }
    }

    static long extendedGDC(long a, long b) {
        //확장 유클리드호제법 시작 x, y 값
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }

        long gcd = extendedGDC(b, a % b);

        //재귀 직후의 x,y값은 아래 단계를 만족하는 값
        //아래 단계: b*prevX + (a%b)*prevY = gcd
        long prevX = x;
        long prevY = y;

        //a%b=a-(a/b)*b : a를 b로 나눴을 떄, b로 나눠 떨어지는 부분 빼면 나머지가 됨
        //b*prevX + (a%b)*prevY
        //-> b*prevX + (a-(a/b)*b)*prevY
        //-> a*prevY + b*(prevX-(a/b)*prevY) (=ax+by형태)
        x = prevY;
        y = prevX - (a / b) * prevY;

        return gcd;
    }
}
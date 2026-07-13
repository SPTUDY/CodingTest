import java.io.*;

public class P042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while (true) {
            if (isPalindrome(n) && isPrime(n)) {
                System.out.println(n);
                break;
            }
            n++;
        }
    }

    static boolean isPalindrome(int n) {
        String str = String.valueOf(n);
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }

        //false를 한번도 마주치지 않으면 true 리턴
        return true;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i * i <= n; i++) {
            //나누어 떨어지면 소수가 아님
            if (n % i == 0) return false;
        }

        //false를 한번도 마주치지 않으면 true 리턴
        return true;
    }
}
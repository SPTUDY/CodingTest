import java.util.*;

public class Main {
    public static final int RANGE = 10_000_001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[RANGE];
        for(int i=2; i<A.length; i++) {
            A[i] = i;
        }

        for(int i=2; i<Math.sqrt(RANGE); i++) {
            if(A[i] == 0) continue;
            for(int j=i*2; j<A.length; j+=i) {
                A[j] = 0;
            }
        }

        int i = N;
        while(true) {
            if(A[i] != 0) {
                int result = A[i];
                if(isPalindrome(result)) {
                    System.out.println(result);
                    break;
                }
            }
            i++;
        }
    }

    public static boolean isPalindrome(int target) {
        char[] c = String.valueOf(target).toCharArray();
        int s = 0;
        int e = c.length - 1;

        while(s < e) {
            if(c[s] != c[e]) {
                return false;
            }
            s++; e--;
        }

        return true;
    }
}
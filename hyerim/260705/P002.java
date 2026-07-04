import java.util.*;

public class P002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
        }
        int max = 0;
        double avg = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] > max) max = s[i];
            avg += (double) s[i];
        }
        System.out.println(avg / max * 100 / n);
    }
}
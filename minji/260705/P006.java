import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int sum = 1, cnt = 1, start = 1, end = 1;

        while(end != n) {
            if(sum > n) {
                sum -= start++;
            }
            else if(sum < n) {
                sum += ++end;
            }
            else {
                cnt++;
                sum += ++end;
            }
        }

        System.out.println(cnt);
    }
}

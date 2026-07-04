import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int start = 0, end = n-1, cnt = 0;

        while(start < end) {
            int sum = arr[start] + arr[end];

            if(sum == m) {
                cnt++;
                start++; end--;
            }
            else if(sum < m) {
                start++;
            }
            else {
                end--;
            }
        }

        System.out.println(cnt);
    }
}

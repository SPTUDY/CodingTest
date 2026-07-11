import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        int start = 0;int end = 0;
        for(int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
            if(arr[i] > start) {
                start = arr[i]; // 레슨의 최대값을 start로 지정
            }
            end += arr[i]; // end는 모든 레슨의 총합
        }

        while(start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            int cnt = 0;

            for(int i=0; i<N; i++) {
                if(sum + arr[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum += arr[i];
            }
            if(sum != 0) cnt++;

            if(cnt > M) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
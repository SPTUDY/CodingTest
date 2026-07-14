import java.util.*;

public class Main {
    public static final int RANGE = 10_000_001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Min = sc.nextLong();
        long Max = sc.nextLong();

        long[] arr = new long[RANGE];
        for(int i=2; i<arr.length; i++) {
            arr[i] = i;
        }

        // 에라토스테네스의 체
        for(int i=2; i<=Math.sqrt(arr.length); i++) {
            if(arr[i] == 0) continue;
            for(int j=i*2; j<arr.length; j+=i) {
                arr[j] = 0;
            }
        }

        int cnt = 0;
        for(int i=2; i<RANGE; i++) {
            if(arr[i] != 0) {
                long temp = arr[i];
                while((double)arr[i] <= (double)Max/(double)temp) {
                    if((double)arr[i] >= (double)Min/(double)temp) {
                        cnt++;
                    }
                    temp *= arr[i];
                }
            }
        }

        System.out.println(cnt);
    }
}
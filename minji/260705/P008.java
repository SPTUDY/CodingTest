import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        int cnt = 0;
        for(int k=0; k<n; k++) {
            long find = arr[k];
            int s = 0;
            int e = n - 1;

            while(s < e) {
                // 좋은 수 찾은 경우
                if(arr[s] + arr[e] == find) {
                    if(s != k && e != k) {
                        cnt++; break;
                    }
                    else if(s == k) {
                        s++;
                    }
                    else if(e == k) {
                        e--;
                    }
                }
                // 합이 더 작을 때
                else if(arr[s] + arr[e] < find) {
                    s++;
                }
                // 합이 더 클 때
                else {
                    e--;
                }
            }
        }

        System.out.println(cnt);
    }
}

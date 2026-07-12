import java.io.*;
import java.util.*;

public class P032 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (binarySearch(target)) sb.append(1).append('\n');
            else sb.append(0).append('\n');
        }

        System.out.println(sb);
    }

    static boolean binarySearch(int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            //타겟이 중앙값과 일치하면 true 반환
            if (arr[mid] == target) return true;
                //타켓이 중앙값보다 크면 오른쪽 dataset 확인
                //left 값 변경
            else if (arr[mid] < target) left = mid + 1;
                //타켓이 중앙값보다 작으면 왼쪽 dataset 확인
                //right 값 변경
            else right = mid - 1;
        }
        return false;
    }
}
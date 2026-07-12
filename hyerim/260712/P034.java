import java.io.*;
import java.util.*;

public class P034 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int left = 1;
        //B[k]는 k보다 작거나 같기 때문에 정답의 최댓값을 k로 설정
        //k 이하인 수가 최소 k개 이상 존재
        //정렬된 배열의 k번째 수는 k보다 클 수 없음
        int right = k;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long count = 0;

            //count = mid보다 작거나 같은 값들의 개수
            //arr[i][j] = i * j 이므로 i번째 열은 i의 배수들로 구성
            //mid/i와 n 중 최솟값이 중앙값보다 작은 값의 개수가 됨
            for (int i = 1; i <= n; i++) count += Math.min(mid / i, n);
            if (count >= k) {       //중앙값 이하 개수가 k보다 크면
                answer = mid;       //해당 중앙값을 정답에 올리고
                right = mid - 1;    //왼쪽 범위 탐색
            } else {            //중앙값 이하 개수가 k보다 작으면
                left = mid +1;  //오른쪽 범위 탐색
            }
        }
        System.out.println(answer);
    }
}
import java.io.*;
import java.util.*;

public class P033 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            //배열 중 최댓값 요소가 블루레이 1개당 최소값이 됨
            //블루레이 크기는 가장 긴 강의보다 짧을 수 없음
            left = Math.max(left, arr[i]);
            //배열의 전체 합이 블루레이 1개당 최댓값 범위가 됨
            //모든 강의를 한 블루레이에 담는 경우가 최댓값 후보
            right += arr[i];
        }
        //정답을 최댓값으로 설정하고 탐색범위 줄이며 이진 탐색
        int answer = right;
        //l이 r보다 커지기 전까지 반복
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid)) {   //만약 사용 개수가 m개 이하면 중앙값 왼쪽 범위 탐색
                answer = mid;
                right = mid - 1;
            } else {    //만약 사용 개수가 m개 초과면 중앙값 오른쪽 범위 탐색
                left = mid + 1;
            }
        }
        System.out.println(answer);

    }

    //size 크기의 블루레이에 모든 강의를 담을 수 있는지 확인
    static boolean check(int size) {
        int count = 1;  //블루레이 개수
        int sum = 0;    //블루레이에 들어간 총 시간
        for (int i = 0; i < n; i++) {
            //현재 블루레이에 이번 강의를 넣었을 때 시간 초과되면
            //블루레이 개수를 하나 추가하고 현재 값으로 sum값 초기화
            //새로운 블루레이 (현재 강의가 첫 강의가 됨)
            if (sum + arr[i] > size) {
                count++;
                sum = arr[i];
            } else {    //그렇지 않으면 sum값에 +현재값
                sum += arr[i];
            }
        }
        return count <= m;  //만약 사용한 블루레이 개수가 m개 이하면 true
    }
}
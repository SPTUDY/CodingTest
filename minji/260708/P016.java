import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arrData[] arr = new arrData[n];

        // 입력받기 (데이터, 기존 인덱스값) 함께 저장
        for(int i=0; i<n; i++) {
            int d = Integer.parseInt(br.readLine());
            arr[i] = new arrData(d, i);
        }

        // 정렬 - O(NlogN)
        Arrays.sort(arr);

        // 정렬 전 idx - 정렬 후 idx 최댓값 찾기
        // 정렬 전 idx - 정렬 후 idx == 해당 원소가 왼쪽으로 몇 칸 이동했는지
        // 모든 원소 중 왼쪽으로 가장 많이 이동한 거리를 찾는 것
        int max = 0;
        for(int i=0; i<n; i++) {
            if(max < arr[i].index - i) {
                max = arr[i].index - i;
            }
        }

        // 버블정렬이 끝난 후 마지막 바퀴 +1
        System.out.println(max+1);
    }

    static class arrData implements Comparable<arrData> {
        int value;
        int index;

        public arrData(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(arrData o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
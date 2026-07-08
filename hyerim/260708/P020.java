import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P020 {
    //입력 배열
    static int[] arr;
    //병합 정렬 후 값 저장 배열
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0, n - 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append('\n');
        }
        System.out.println(sb);
    }

    //배열을 반절로 나누는 함수
    public static void mergeSort(int s, int e) {
        //원소가 1개 이하면 이미 정렬된 상태
        if (s >= e) return;
        int m = (s + e) / 2;
        //왼쪽 절반 정렬
        mergeSort(s, m);
        //오른쪽 절반 정렬
        mergeSort(m + 1, e);
        //정렬된 두 구간을 합침
        merge(s, m, e);
    }

    //배열 정렬하고 합치는 함수
    public static void merge(int s, int m, int e) {
        int l = s;
        int r = m + 1;
        int i = s;

        //왼쪽과 오른쪽 구간을 비교하면서 작은 값부터 temp에 저장
        //왼쪽 그룹 인덱스가 중간 인덱스보다 작고
        //오른쪽 그룹 인덱스가 끝 인덱스보다 작을 동안
        while (l <= m && r <= e) {
            //각 인덱스 안의 값 비교해서 temp에 넣고 각각 인덱스 증가
            if (arr[l] <= arr[r]) temp[i++] = arr[l++];
            else temp[i++] = arr[r++];
        }
        //한쪽 그룹이 모두 temp 안에 들어긴 뒤
        //나머지 그룹의 남은 원소들을 temp 배열에 차례대로 저장
        while (l <= m) temp[i++] = arr[l++];
        while (r <= e) temp[i++] = arr[r++];
        //temp에 저장된 값을 다시 arr에 복사
        for (int j = s; j <= e; j++) {
            arr[j] = temp[j];
        }
    }
}
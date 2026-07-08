import java.io.*;

public class Main {
    // A - 실제 정렬할 배열
    // tmp - 정렬 전 잠시 배열값 복사용
    public static int[] A, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new int[N];
        tmp = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        // 0 ~ N-1 범위 정렬
        mergeSort(0, N-1);

        for(int i=0; i<N; i++) {
            System.out.println(A[i]);
        }
    }

    private static void mergeSort(int s, int e) {
        // s == e 이면 원소 1개임 -> 나눌 필요X
        if(e - s < 1) return;

        // 중간 지점 나눠서 왼쪽, 오른쪽 각각 병합정렬 수행
        int m = s + (e-s)/2;
        mergeSort(s,m);
        mergeSort(m+1, e);

        // 현재 정렬할 구간 A[s] ~ A[e]을 tmp에 복사해
        for(int i=s; i<=e; i++) {
            tmp[i] = A[i];
        }

        // 두 그룹 병합
        int k = s; // k는 실제 A배열에 값을 채워넣을 위치
        int idx1 = s; // 왼쪽 그룹 시작 인덱스 (s ~ m)
        int idx2 = m+1; // 오른쪽 그룹 시작 인덱스 (m+1 ~ e)

        while(idx1 <= m && idx2 <= e) {
            // 왼쪽 값이 오른쪽 값보다 크면, 더 작은 오른쪽 값을 A배열에 넣음
            if(tmp[idx1] > tmp[idx2]) {
                A[k++] = tmp[idx2++];
            }
            else {
                A[k++] = tmp[idx1++];
            }
        }

        // 한쪽 그룹 끝나고 남아있는 값 정리
        while(idx1 <= m) {
            A[k++] = tmp[idx1++];
        }
        while(idx2 <= e) {
            A[k++] = tmp[idx2++];
        }
    }
}
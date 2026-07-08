import java.util.*;
import java.io.*;

public class Main {
    public static int[] A;
    public static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0, N-1);
        System.out.println(A[K-1]);
    }

    public static void quickSort(int start, int end) {
        if(start < end) {
            int pivot = partition(start, end);
            if(pivot == K-1) {
                return;
            }
            else if(K-1 < pivot) { // K가 pivot 왼쪽 그룹에 있음
                quickSort(start, pivot-1);
            }
            else { // K가 pivot 오른쪽 그룹에 있음
                quickSort(pivot+1, end);
            }
        }
    }

    public static int partition(int start, int end) {
        // start와 end가 인접한 경우
        if(start+1 == end) {
            if(A[start] > A[end]) {
                swap(start, end);
            }
            return end;
        }

        // 가운데값
        int mid = (start + end) / 2;
        swap(start, mid); // 이동의 편의성을 위해 start값과 mid값을 swap함

        int pivot = A[start];
        int s = start + 1;
        int e = end;

        while(s <= e) {
            // 오른쪽 그룹 확인. pivot보다 작은 수가 나올때까지 인덱스--
            while(e >= s+1 && pivot < A[e]) {
                e--;
            }
            // 왼쪽 그룹 확인. pivot보다 큰 수가 나올때까지 인덱스++
            while(s <= e && pivot > A[s]) {
                s++;
            }
            // 찾은 s,e 데이터를 스왑
            if(s <= e) {
                swap(s++, e--);
            }
        }

        // start 자리에 있던 pivot을 최종 위치 e로 이동
        swap(start, e);

        // pivot의 최종 인덱스 반환
        return e;
    }

    public static void swap(int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
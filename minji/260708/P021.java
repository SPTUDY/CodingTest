import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] A, tmp;
    public static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new int[N];
        tmp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, N-1);
        System.out.println(answer);
    }

    private static void mergeSort(int s, int e) {
        if(e-s < 1) return;

        int m = s + (e-s)/2;
        mergeSort(s, m);
        mergeSort(m+1, e);

        for(int i=s; i<=e; i++) {
            tmp[i] = A[i];
        }

        int k = s;
        int idx1 = s;
        int idx2 = m+1;

        while(idx1 <= m && idx2 <=e) {
            // 뒤쪽 데이터의 값이 더 작은 경우
            if(tmp[idx1] > tmp[idx2]) {
                answer += (idx2 - k);   // 해당 차이만큼 swap해서 오는 것이므로 answer 업데이트
                                        // 오른쪽 값이 왼쪽 값보다 작아서 앞으로 이동해야 하는 거리
                A[k++] = tmp[idx2++];
            }
            else {
                A[k++] = tmp[idx1++];
            }
        }

        while(idx1 <= m) {
            A[k++] = tmp[idx1++];
        }
        while(idx2 <= e) {
            A[k++] = tmp[idx2++];
        }
    }
}
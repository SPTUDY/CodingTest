import java.io.*;

public class Main {
    public static int[] A;
    public static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        // 5는 문제 조건에서 수의 범위가 10,000 이하인 자연수이므로
        // 최대 자릿수인 5를 뜻함
        radixSort(5);
        for(int i=0; i<N; i++) {
            bw.write(A[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void radixSort(int max_jarisu) {
        int[] output = new int[A.length]; // 현재 자릿수 기준으로 정렬된 결과 임시 저장용

        int jarisu = 1; // 현재 확인중인 자릿수 (일의 자리부터 시작)
        int cnt = 0;

        /**
         * 특정 자릿수의 값을 뽑는 방법: (A[i] / jarisu) % 10
         * 예시) 753의 십의 자릿수를 뽑는 경우
         * (753 / 10) % 10
         * = 75 % 10
         * = 5
         */

        while(cnt != max_jarisu) {
            int[] bucket = new int[10]; // 현재 자릿수의 숫자 개수를 세는 배열 (0~9)

            // 현재 자릿수 개수 세기
            for(int i=0; i<A.length; i++) {
                bucket[(A[i] / jarisu) % 10]++;
            }

            // 합 배열로 index 계산 (output 저장용)
            for(int i=1; i<10; i++) {
                bucket[i] += bucket[i - 1];
            }

            // 현재 자릿수를 기준으로 정렬
            // 뒤에서부터 순회해야 안정 정렬이 유지됨
            for(int i=A.length-1; i>=0; i--) {
                // 1. (A[i] / jarisu % 10) : A[i]의 자릿수를 구하고
                // 2. bucket[...] : 그 자릿수에 해당하는 bucket 값을 이용해서
                // 3. output[...] : output 배열에 담을 위치를 찾아 A[i]를 넣는다
                output[bucket[(A[i] / jarisu % 10)] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }

            // 다음 자릿수로 이동하기 위해 현재 자리수 기준 정렬 데이터 저장
            for(int i=0; i<A.length; i++) {
                A[i] = output[i];
            }

            jarisu *= 10; // 자릿수 증가
            cnt++;
        }
    }
}
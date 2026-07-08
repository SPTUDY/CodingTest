import java.io.*;
import java.util.*;

public class P022 {
    public static int[] arr;
    public static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        //주어진 수가 10,000보다 작거나 같기 때문에 제일 큰 자릿수 = 5개
        radixSort(arr, 5);
        for (int i = 0; i < n; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void radixSort(int[] arr, int max) {
        //정렬된 결과를 잠깐 담아둘 배열
        int[] output = new int[arr.length];
        int k = 1;
        int count = 0;
        //버킷 일의 자릿수, ..., 만의 자릿수까지
        //최대 5번 봄
        //버킷 자릿수 바뀔 때마다 count++
        while (count != max) {
            int[] bucket = new int[10];
            for (int i = 0; i < arr.length; i++) {
                //k의 자릿수 정렬 -> k는 1부터 *10씩 증가
                //각 버킷에 몇개가 들어가는지만 들어가 있음
                bucket[(arr[i] / k % 10)]++;
            }
            //버킷의 누적합
            //각 버킷에 몇개가 들어가는지 누적합
            //예를 들어 첫번째 수행에서 bucket[3] = 2라면 1의 자리가 3 이하인 수는 2개
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }
            //현재 기준 자릿수를 기준으로 데이터를 정렬
            //데이터를 뒤에서부터 탐색: 같은 자리수끼리 원래 순서를 유지하기 위해
            for (int i = arr.length - 1; i >= 0; i--) {
                //예를 들어 bucket[3] = 2면, 1의 자리가 3 이하인 수가 2개
                //output에서 1번 인덱스까지 차지
                output[bucket[(arr[i] / k) % 10] - 1] = arr[i];
                //output 배열에 넣고 나면 bucket에서 빼주기
                //같은 자릿수 원소가 output 배열에서 알맞은 순서로 들어갈 수 있도록
                bucket[(arr[i] / k) % 10]--;
            }
            //임시 배열에 넣은 값을 arr로 복사
            for (int i = 0; i < arr.length; i++) {
                arr[i] = output[i];
            }
            //k는 1부터 10000까지 늘어나며
            //k의 자릿수 버킷을 찾아가도록 함
            k = k * 10;
            count++;
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // k 구해서 트리 초기화하기
        int k = 0;
        while(true) {
            if(Math.pow(2, k) >= N) break;
            k++;
        }
        int treeLen = (int) Math.pow(2, k+1);
        tree = new long[treeLen];

        // 트리에 원본 배열 넣기
        int startIdx = (int) Math.pow(2, k);
        for(int i=startIdx; i<startIdx+N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        // 부모로 올라가면서 구간합 구하기
        for(int i=treeLen-1; i>=1; i--) {
            tree[i/2] += tree[i];
        }

        // 질의 수행 (1이면 값 변경, 2이면 구간합 출력)
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int question = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            // 값 변경
            if(question == 1) {
                changeNum(startIdx + a - 1, b);
            }
            // 구간합 출력
            else {
                System.out.println(getSum(startIdx + a - 1, startIdx + (int)b - 1));
            }
        }
    }

    public static void changeNum(int index, long num) {
        long diff = num - tree[index];
        while(index > 0) {
            tree[index] += diff;
            index /= 2;
        }
    }

    public static long getSum(int s, int e) {
        long partSum = 0;
        while(s <= e) {
            if(s%2 == 1) {
                partSum += tree[s];
                s++;
            }
            if(e%2 == 0) {
                partSum += tree[e];
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return partSum;
    }
}
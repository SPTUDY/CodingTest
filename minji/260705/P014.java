import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int first_abs = Math.abs(a);
            int second_abs = Math.abs(b);
            if(first_abs == second_abs) {
                return a > b ? 1 : -1; // 절댓값이 같은 경우 음수 우선 정렬
            }
            else {
                return first_abs - second_abs; // 절댓값 기준으로 정렬
            }
        });

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                if(pq.isEmpty()) {
                    System.out.println("0");
                }
                else {
                    System.out.println(pq.poll());
                }
            }
            else {
                pq.add(num);
            }
        }
    }
}
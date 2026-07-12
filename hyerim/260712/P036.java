import java.io.*;
import java.util.*;

public class P036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //작은 값이 자동으로 먼저 나오는 우선순위큐
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;

        //제일 작은 값과 그 다음으로 작은 값 꺼내서 더함
        //더한 값을 다시 우선순위 큐에 넣음
        //카드 덱이 2개 이상인 동안 반복: 하나만 남으면 더이상 비교할 필요 없음
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            answer += sum;
            pq.add(sum);
        }

        System.out.println(answer);
    }
}
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수의 개수, 윈도우 길이
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();

        for(int i=0; i<N; i++) {
            int now = Integer.parseInt(st.nextToken());

            // 새로 들어온 숫자보다 이미 있는 숫자들이 크면 삭제 (최솟값 될 확률이 없음)
            while(!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }

            // (새로 들어온 값, 인덱스) 넣기
            deque.addLast(new Node(now, i));

            // 윈도우 범위 벗어난 노드 제거
            // 유효범위: i-L+1 ~ i
            if(deque.getFirst().index < i-L+1) {
                deque.removeFirst();
            }
            bw.write(deque.getFirst().value + " ");
        }

        bw.flush();
        bw.close();
    }

    public static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

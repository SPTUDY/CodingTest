import java.io.*;
import java.util.*;

public class P010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Deque<Node> myDeque = new LinkedList<>();

        //덱에 들어가는 값들은 오름차순으로 정렬된 상태
        //원리는 마지막에 위치한 값이 들어갈 값보다 크면 마지막 원소 제거하므로
        //덱[1 5] <- 2인 경우, 마지막 원소 값이 2보다 크기 때문에 제거
        //덱[1 2]가 됨
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());
            //만약 덱이 비어있지 않고, 마지막 원소 값이 현재 값보다 크면 마지막 원소 제거
            while (!myDeque.isEmpty() && myDeque.getLast().value > now) {
                myDeque.removeLast();
            }
            //덱의 마지막에 현재 원소 추가
            myDeque.addLast(new Node(now, i));
            //윈도우 크기 넘어가면 덱의 맨 앞 원소 제거
            //맨 앞 원소 인덱스가 현재 들어갈 값의 인덱스-윈도우 크기보다 작으면
            //맨 앞 원소 제거해야됨
            if (myDeque.getFirst().index <= i - size) {
                myDeque.removeFirst();
            }
            //제일 앞에 위치한 요소(=최소값) bw에 쓰기
            bw.write(myDeque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();

    }

    static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

}
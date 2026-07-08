import java.io.*;
import java.util.*;

public class P016 {

    //인덱스와 값을 함께 저장해야되므로 Node 클래스 선언
    static class Node {
        int value;
        int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    //원래 인덱스 - 정렬 후 인덱스 = 원소가 왼쪽으로 몇번 이동했는지 알 수 있음 (+)
    public static void main(String[] args) throws IOException {
        //버블정렬에서 원소가 swap될 때
        //원소는 왼쪽으로 최대 한칸, 오른쪽으로는 여러칸 갈 수 있음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = new Node(value, i);
        }

        //음수가 리턴되면 o1<o2
        //양수가 리턴되면 o1>o2 -> swap
        Arrays.sort(arr, (o1, o2) -> o1.value - o2.value);

        int answer = 0;
        //원래 인덱스 arr[i].index
        //정렬 후 인덱스 i
        //둘의 차를 계산해서 최댓값 구하기
        for (int i = 0; i < n; i++) {
            int move = arr[i].index - i;
            answer = Math.max(answer, move);
        }

        System.out.println(answer + 1);

    }
}
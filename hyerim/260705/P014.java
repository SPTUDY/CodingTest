import java.util.*;

public class P014 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //우선순위 큐 로직
        //음수가 리턴되면 o1이 먼저
        //양수가 리턴되면 o2가 먼저
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
            //절댓값 작은 데이터 우선
            int first = Math.abs(o1);
            int second = Math.abs(o2);
            //절댓값이 같은 경우 음수 우선
            if (first == second) {
                //o1이 양수면 1 리턴 (o2가 먼저)
                //o1이 음수면 -1 리턴 (o1이 먼저)
                return o1 > o2 ? 1 : -1;
            }
            //양수가 리턴되면 o1이 더 큼 (o2가 먼저)
            //음수가 리턴되면 o2가 더 큼 (o1이 먼저)
            return first - second;
        });
        int n = sc.nextInt();

        //0이 입력되었을 때 배열에서 절댓값 제일 작은 값 출력 (절댓값 같으면 음수 먼저)
        //출력된 값은 배열에서 삭제
        //x!=0일때 배열에 값 추가
        //배열이 비어있을 때는 0 출력
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x == 0) {
                if (q.isEmpty()) System.out.println("0");
                else System.out.println(q.poll());
            } else {
                q.add(x);
            }
        }
    }
}
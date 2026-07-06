import java.io.*;
import java.util.*;

public class P013 {
    //카드게임: 맨 윗장 버리고, 다음장은 제일 아래에 두고 -> 반복하여 마지막에 남는 카드가 무엇인지 출력
    //카드는 1이 제일 위에 쌓여있고 아래로 갈 수록 숫자가 커짐
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();

        //큐에 카드 덱 생성
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        //1장만 남을때까지 반복
        while (q.size() > 1) {
            //맨 윗장 버리기
            q.poll();
            //다음장 아래에 깔기
            q.add(q.poll());
        }
        //마지막장 출력
        System.out.println(q.poll());
    }
}
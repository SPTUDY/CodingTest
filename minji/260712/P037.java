import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        PriorityQueue<Integer> plusQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusQ = new PriorityQueue<>();
        int one = 0;
        int zero = 0;

        for(int i=0; i<N; i++) {
            int data = sc.nextInt();
            if(data > 1) {
                plusQ.add(data);
            }
            else if (data == 1) {
                one++;
            }
            else if (data == 0) {
                zero++;
            }
            else {
                minusQ.add(data);
            }
        }

        int sum = 0;

        // 양수 처리
        while(plusQ.size() > 1) {
            int a = plusQ.poll();
            int b = plusQ.poll();
            sum += (a * b);
        }
        if(!plusQ.isEmpty()) {
            sum += plusQ.poll();
        }

        // 음수 처리
        while(minusQ.size() > 1) {
            int a = minusQ.poll();
            int b = minusQ.poll();
            sum += (a * b);
        }
        if(!minusQ.isEmpty() && zero == 0) {
            sum += minusQ.poll();
        }

        // 1 처리
        sum += one;

        System.out.println(sum);
    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
            pq.add(sc.nextInt());
        }

        int data1 = 0;
        int data2 = 0;
        int sum = 0;
        while(pq.size() != 1) {
            data1 = pq.poll();
            data2 = pq.poll();
            sum += data1 + data2;
            pq.add(data1 + data2);
        }

        System.out.println(sum);
    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer>[] list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        int[] indegree = new int[N+1];
        for(int i=0; i<M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            list[s].add(e);
            indegree[e]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");
            for(int next : list[now]) {
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
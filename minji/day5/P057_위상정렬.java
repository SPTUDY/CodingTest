import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        int[] indegree = new int[N+1];
        int[] time = new int[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == -1) break;
                list[temp].add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] answer = new int[N+1];
        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next : list[now]) {
                indegree[next]--;
                answer[next] = Math.max(answer[next], answer[now] + time[now]);
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for(int i=1; i<=N; i++) {
            System.out.println(answer[i] + time[i]);
        }
    }
}
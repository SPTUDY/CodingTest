import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }

        answer = new int[N+1];
        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            BFS(i);
        }

        int max = 0;
        for(int i=1; i<=N; i++) {
            max = Math.max(max, answer[i]);
        }

        for(int i=1; i<=N; i++) {
            if(answer[i] == max) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next : list[now]) {
                if(!visited[next]) {
                    visited[next] = true;
                    answer[next]++;
                    q.add(next);
                }
            }
        }
    }
}
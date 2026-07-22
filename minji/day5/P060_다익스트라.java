import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];
        dist = new int[N+1];
        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e,w));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 다익스트라
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0));
        dist[S] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(visited[now.node]) continue;
            visited[now.node] = true;
            
            for(Edge next : graph[now.node]) {
                if(dist[next.node] > dist[now.node] + next.value) {
                    dist[next.node] = dist[now.node] + next.value;
                    pq.offer(new Edge(next.node, dist[next.node]));
                }
            }
        }

        System.out.println(dist[E]);
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int value;
        Edge(int node, int value) {
            this.node = node;
            this.value = value;
        }
        public int compareTo(Edge e) {
            return Integer.compare(this.value, e.value);
        }
    }
}
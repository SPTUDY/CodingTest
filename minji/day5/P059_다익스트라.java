import java.io.*;
import java.util.*;

public class Main {
    static int V, E, K;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 노드 개수
        E = Integer.parseInt(st.nextToken()); // 엣지 개수
        K = Integer.parseInt(br.readLine()); // 출발 노드

        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[V+1];
        dist = new int[V+1];
        for(int i=1; i<=V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e,w));
        }

        pq.add(new Edge(K,0));
        dist[K] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(visited[now.node]) continue; // 이미 방문한 적 있으면 큐에 넣지X
            visited[now.node] = true;

            for(int i=0; i<graph[now.node].size(); i++) {
                Edge next = graph[now.node].get(i);

                // 최소 거리로 업데이트
                if(dist[next.node] > dist[now.node] + next.weight) {
                    dist[next.node] = dist[now.node] + next.weight;
                    pq.add(new Edge(next.node, dist[next.node]));
                }
            }
        }

        for(int i=1; i<=V; i++) {
            if(visited[i]) {
                System.out.println(dist[i]);
            }
            else {
                System.out.println("INF");
            }
        }

    }

    static class Edge implements Comparable<Edge> {
        int node;
        int weight;
        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        public int compareTo(Edge e) {
            if(this.weight > e.weight) return 1;
            else return -1;
        }
    }
}
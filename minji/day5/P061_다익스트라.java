import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    // graph[i]는 i에서 출발하는 간선 목록
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Integer>[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new PriorityQueue[N+1];


        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            // dist는 도시별 거리 저장용 - 가장 큰 값을 먼저 꺼내야하므로 reverse
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e,w));
        }

        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 전체 탐색용 큐
        pq.offer(new Node(1,0));
        dist[1].offer(0); // 시작도시 1번까지의 거리는 0

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            // 현재 도시에서 갈 수 있는 모든 다음 도시 확인
            for(Edge edge : graph[now.node]) {
                int next = edge.node;
                int newDist = now.dist + edge.cost;

                if(dist[next].size() < K) {
                    dist[next].offer(newDist);
                    pq.offer(new Node(next, newDist));
                }

                else if(dist[next].peek() > newDist) {
                    dist[next].poll();
                    dist[next].offer(newDist);
                    pq.offer(new Node(next, newDist));
                }

            }
        }

        for(int i=1; i<=N; i++) {
            if(dist[i].size() == K) {
                System.out.println(dist[i].peek());
            }
            else {
                System.out.println(-1);
            }
        }

    }

    static class Edge {
        int node; int cost;
        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int node; int dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        // 누적 거리 작은 게 먼저 나오도록 함
        public int compareTo(Node n) {
            return Integer.compare(this.dist, n.dist);
        }
    }
}
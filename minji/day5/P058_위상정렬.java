import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static ArrayList<Edge>[] reverseGraph;
    static int[] indegree;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 도시 수
        M = Integer.parseInt(br.readLine()); // 도시 수

        graph = new ArrayList[N+1];
        reverseGraph = new ArrayList[N+1];
        indegree = new int[N+1];
        distance = new int[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(e,v));
            reverseGraph[e].add(new Edge(s,v));
            indegree[e]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 위상 정렬
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()) {
            int now = q.poll();
            for(Edge next : graph[now]) {
                indegree[next.node]--;
                distance[next.node] = Math.max(
                        distance[next.node],
                        distance[now] + next.value
                );

                if(indegree[next.node] == 0) {
                    q.offer(next.node);
                }
            }
        }

        // 위상 정렬 reverse
        int answer = 0;
        boolean[] visited = new boolean[N+1];
        q = new LinkedList<>();
        q.offer(end);
        visited[end] = true;
        while(!q.isEmpty()) {
            int now = q.poll();
            for(Edge next : reverseGraph[now]) {
                // 1분도 쉬지 않는 도로인지 체크
                if(distance[next.node] + next.value == distance[now]) {
                    answer++;
                    if(!visited[next.node]) {
                        visited[next.node] = true;
                        q.offer(next.node);
                    }
                }
            }
        }

        System.out.println(distance[end]);
        System.out.println(answer);
    }

    static class Edge {
        int node;
        int value;
        public Edge(int nextNode, int value) {
            this.node = nextNode;
            this.value = value;
        }
    }
}
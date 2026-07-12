import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Edge>[] list;
    public static int[] dis;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        // 연결리스트 입력받기
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 노드 번호

            while(true) {
                int e = Integer.parseInt(st.nextToken());
                if(e == -1) break;
                int w = Integer.parseInt(st.nextToken());
                list[n].add(new Edge(e,w));
            }
        }

        int max_index = 1;

        // 1차 bfs 수행
        dis = new int[N+1];
        visited = new boolean[N+1];
        bfs(1);
        for(int i=2; i<=N; i++) {
            if(dis[max_index] < dis[i]) {
                max_index = i;
            }
        }

        // 2차 bfs 수행
        dis = new int[N+1];
        visited = new boolean[N+1];
        bfs(max_index);

        Arrays.sort(dis);
        System.out.println(dis[N]);
    }


    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(Edge next : list[now]) {
                if(!visited[next.node]) {
                    visited[next.node] = true;
                    q.add(next.node);
                    dis[next.node] = dis[now] + next.weight;
                }
            }
        }
    }

    private static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
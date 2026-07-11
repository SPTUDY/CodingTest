import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드의 개수
        int M = Integer.parseInt(st.nextToken()); // 에지의 개수
        int V = Integer.parseInt(st.nextToken()); // 탐색 시작 노드 번호

        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        // 노드 번호 작은 것부터 방문하도록 정렬
        for(int i=1; i<=N; i++) {
            Collections.sort(list[i]);
        }

        br.close();

        // dfs 수행
        visited = new boolean[N+1];
        dfs(V);
        sb.append("\n");

        // bfs 수행
        visited = new boolean[N+1];
        bfs(V);

        System.out.println(sb);
    }

    private static void dfs(int node) {
        visited[node] = true;
        sb.append(node + " ");

        for(int next : list[node]) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }

    private static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        visited[node] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now + " ");

            for(int next: list[now]) {
                if(!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}
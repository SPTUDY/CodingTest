import java.io.*;
import java.util.*;

public class P051 {

    static int v, e;
    static ArrayList<Integer>[] graph;
    static int[] visited;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            graph = new ArrayList[v + 1];
            visited = new int[v + 1];    //0으로 초기화 됨 -> 미방문이면 0
            isBipartite = true;

            for (int j = 1; j <= v; j++) graph[j] = new ArrayList<>();
            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                //무방향 그래프
                graph[a].add(b);
                graph[b].add(a);
            }

            //그래프가 하나로 연결되어 있지 않을 수도 있음
            //방문하지 않은 모든 정점에서 BFS를 돌려서 확인 필요
            for (int j = 1; j <= v; j++) {
                if (visited[j] == 0) BFS(j);    //방문하지 않은 정점이면 BFS 실행
                if (!isBipartite) break;        //이분그래프가 아니면 종료
            }
            if (isBipartite) sb.append("YES").append('\n');    //이분그래프라면
            else sb.append("NO").append('\n');                 //이분그래프가 아니라면
        }

        System.out.println(sb);
    }

    static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = 1;     //처음 방문 노드를 1번 그룹에 넣음
        queue.add(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now]) {
                if (visited[next] == 0) {           //만약 방문하지 않은 정점이면
                    visited[next] = -visited[now];  //현재 노드와 반대 그룹에 넣음
                    queue.add(next);
                } else if (visited[next] == visited[now]) { //이미 방문했는데 현재 노드와 같은 그룹이면
                    isBipartite = false;                    //이분그래프 판별 변수 false 처리 후 리턴
                    return;
                }
            }
        }
    }
}
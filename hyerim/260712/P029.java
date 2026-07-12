import java.io.*;
import java.util.*;

public class P029 {
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];

        //인접 그래프 만들기
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //무방향 그래프이므로 양쪽에 추가
            arr[a].add(b);
            arr[b].add(a);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(arr[i]);
        }
        visited = new boolean[n + 1];
        DFS(v);
        sb.append('\n');

        //BFS 전 DFS에서 사용한 visited 배열 초기화
        visited = new boolean[n + 1];
        BFS(v);

        System.out.println(sb);
    }

    //DFS
    static void DFS(int node) {
        //방문 노드로 변경하고 sb에 추가
        visited[node] = true;
        sb.append(node).append(' ');
        //현재 노드와 연결된 노드 확인
        for (int next : arr[node]) {
            //만약 미방문 노드이면, 해당 노드에서 DFS 재귀 실행
            if (!visited[next]) {
                DFS(next);
            }
        }
    }

    //BFS
    static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        //시작 노드를 방문 처리 및 큐에 추가
        visited[node] = true;
        queue.add(node);
        //큐가 비게 될 때까지
        while (!queue.isEmpty()) {
            //큐에서 하나 꺼내기 및 출력
            int now = queue.poll();
            sb.append(now).append(' ');

            //방금 꺼낸 노드와 연결된 노드 중 미방문 노드 방문
            //방문 처리 및 큐에 추가
            for (int next : arr[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
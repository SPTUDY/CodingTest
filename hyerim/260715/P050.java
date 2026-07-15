import java.io.*;
import java.util.*;

public class P050 {

    static int n, m;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];

        //인접리스트 만들기
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //a가 b를 신뢰
            //b가 해킹되면 a도 같이 해킹됨
            //따라서 방향은 b->a
            graph[b].add(a);
        }

        int[] count = new int[n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            count[i] = BFS(i);
            max = Math.max(count[i], max);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            //max값을 가지는 i를 출력
            if (count[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        //처음 시작 노드는 미방문 노드 -> 방문 표시하고 큐에 추가
        visited[start] = true;
        queue.add(start);

        int count = 1;
        //큐가 비어있지 않은 동안 BFS
        while (!queue.isEmpty()) {
            int now = queue.poll();
            //다음 노드 미방문이면 방문으로 표시 하고 큐에 추가
            //방문 횟수 배열의 횟수 증가
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }
        return count;
    }
}
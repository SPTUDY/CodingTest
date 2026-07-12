import java.io.*;
import java.util.*;

public class P025 {
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            //어디가 시작점일지 모르기 때문에 i는 0부터 n-1까지 모두 탐색
            //i는 방문했기 때문에 true로 표시
            visited[i] = true;
            DFS(i, 1);
            //i부터 DFS가 끝나면 다시 방문을 풀어줌
            //그 이유는 다시 다른 지점부터 시작해야되기 때문에
            visited[i] = false;
            if (found) break;
        }

        //found = true이면 1 출력
        //found = false이면 0 출력
        System.out.println(found ? 1 : 0);

    }

    static void DFS(int now, int depth) {
        //깊이가 5라는 것은 조건 만족했으므로 1 출력
        if (depth == 5) {
            found = true;
            return;
        }

        //arr[now]와 연결된 연결리스트(인접리스트) 탐색
        for (int next : arr[now]) {
            //만약 방문하지 않았으면
            //방문으로 표시하고, 다음 친구를 탐색하며 깊이 +1
            if (!visited[next]) {
                visited[next] = true;
                DFS(next, depth + 1);
                //다른 경로에서 해당 친구를 빼먹지 않도록 방문 풀어줌
                visited[next] = false;
            }

            if (found) return;
        }
    }
}
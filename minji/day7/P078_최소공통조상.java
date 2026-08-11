import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static boolean[] visited;
    static int kmax;
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 트리 연결 정보 저장
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        depth = new int[N + 1];
        visited = new boolean[N + 1];

        // 2^k가 N 이상이 될 때까지 kmax 계산
        int temp = 1;
        kmax = 0;

        while(temp <= N) {
            temp <<= 1;
            kmax++;
        }

        parent = new int[kmax + 1][N + 1];

        // depth와 바로 위 부모 구하기
        bfs(1);

        // 2^k번째 부모 구하기
        for(int k = 1; k <= kmax; k++) {
            for(int node = 1; node <= N; node++) {
                parent[k][node]
                    = parent[k - 1][parent[k - 1][node]];
            }
        }

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(executeLCA(a, b)).append('\n');
        }

        System.out.print(sb);
    }

    static int executeLCA(int a, int b) {

        // b가 더 깊은 노드가 되도록 설정
        if(depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 두 노드의 깊이 맞추기
        int diff = depth[b] - depth[a];

        for(int k = kmax; k >= 0; k--) {
            if((diff & (1 << k)) != 0) {
                b = parent[k][b];
            }
        }

        // 깊이를 맞췄더니 같은 노드라면 바로 LCA
        if(a == b) {
            return a;
        }

        // LCA 바로 아래까지 동시에 이동
        for(int k = kmax; k >= 0; k--) {
            if(parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        return parent[0][a];
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : tree[now]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);

                    parent[0][next] = now;
                    depth[next] = depth[now] + 1;
                }
            }
        }
    }
}
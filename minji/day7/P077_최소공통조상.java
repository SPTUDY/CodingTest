import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 개수 : N
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) {
            tree[i] = new ArrayList<>();
        }

        // 정점 연결 정보 저장
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        // LCA 세팅
        depth = new int[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        BFS(1); // 루트는 1

        // LCA 구할 정점 쌍의 개수 : M
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(LCA(a,b));
        }
    }

    public static int LCA(int a, int b) {
        // a를 더 깊은 노드로 맞춤
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 두 노드의 depth 맞춰주기
        while(depth[a] != depth[b]) {
            a = parent[a]; // a의 depth를 1씩 올림
        }

        // 같은 조상 나올 때까지 올리기
        while(a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    public static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int next : tree[now]) {
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(next);

                    // next의 부모는 현재 노드
                    parent[next] = now;

                    // 자식의 깊이 = 부모의 깊이 + 1
                    depth[next] = depth[now] + 1;
                }
            }
        }
    }
}
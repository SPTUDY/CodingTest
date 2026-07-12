import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int E = Integer.parseInt(st.nextToken()); // 에지 개수

        // 인접 리스트, 방문 배열 초기화
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];

        // 에지 입력받아서 인접 리스트 채우기
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        // 모든 노드 DFS 확인
        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                answer++;
                DFS(i);
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int node) {
        visited[node] = true;

        for(int next : list[node]) {
            if(!visited[next]) {
                DFS(next);
            }
        }
    }
}
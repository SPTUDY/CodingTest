import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static boolean isArrive = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int E = Integer.parseInt(st.nextToken()); // 에지 개수

        // 인접 리스트, 방문 배열 초기화
        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N];

        // 인접 리스트 채우기
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        // 친구 관계 있는지 확인
        for(int i=0; i<N; i++) {
            DFS(i, 1);
            if(isArrive) break;
        }

        if(isArrive) {
            System.out.println("1");
        }
        else {
            System.out.println("0");
        }
    }

    private static void DFS(int now, int depth) {
        if(depth == 5 || isArrive) {
            isArrive = true;
            return;
        }

        // 백트래킹 - 탐색이 끝나면 visited를 false로 변경
        // 다른 노드에서 다시 해당 노드를 사용할 수 있도록 함
        visited[now] = true;
        for(int next : list[now]) {
            if(!visited[next]) {
                DFS(next, depth+1);
            }
        }
        visited[now] = false;
    }
}
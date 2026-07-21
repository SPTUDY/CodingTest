import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static ArrayList<Integer>[] list;
    static int[] visited;
    static List<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 도시 정보

        answer = new ArrayList<>();
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }

        visited = new int[N+1];
        for(int i=1; i<=N; i++) {
            visited[i] = -1;
        }

        BFS(X);

        for(int i=1; i<=N; i++) {
            if(visited[i] == K) {
                answer.add(i);
            }
        }

        if(answer.isEmpty()) {
            System.out.println("-1");
        }
        else {
            Collections.sort(answer);
            for(int a : answer) {
                System.out.println(a);
            }
        }
    }

    public static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start]++;

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int i : list[now]) {
                if(visited[i] == -1) {
                    visited[i] = visited[now] + 1;
                    q.add(i);
                }
            }
        }
    }
}
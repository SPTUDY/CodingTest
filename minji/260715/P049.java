import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
    static int visited[];
    static List<Integer> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 노드의 수
        int M = sc.nextInt(); // 엣지의 수
        int K = sc.nextInt(); // 목표 거리
        int X = sc.nextInt(); // 시작점

        answer = new ArrayList<>();
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            list[s].add(e);
        }

        visited = new int[N+1];
        for(int i=0; i<=N; i++) {
            visited[i] = -1;
        }

        bfs(X);
        for(int i=0; i<=N; i++) {
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

    public static void bfs(int start) {
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
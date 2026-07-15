import java.io.*;
import java.util.*;

public class P049 {

    static int n, m, k, x;
    static ArrayList<Integer>[] graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        distance = new int[n + 1];

        //거리배열 초기화
        //-1이라는 것은 아직 미방문 도시라는 뜻
        Arrays.fill(distance, -1);
        //인접리스트 초기화
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        //단방향 도로 입력: a->b로 이동
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        BFS(x);

        StringBuilder sb = new StringBuilder();
        boolean found = false;  //찾았는지 확인하는 변수

        //거리가 k인 i는 sb에 추가하고 찾았다고 표기
        //1부터 시작하면 자동으로 오름차순 출력됨
        for (int i = 1; i <= n; i++) {
            if (distance[i] == k) {
                sb.append(i).append('\n');
                found = true;
            }
        }

        if(!found) System.out.println(-1);  //조건 만족하는 값이 없으면 -1 출력
        else System.out.println(sb);
    }

    static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        //출발 도시의 거리는 0
        distance[start] = 0;
        queue.add(start);

        //큐가 비어있지 않은 동안
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now])
                //만약 미방문 도시라면 현재까지의 거리에서 +1
                if (distance[next] == -1) {
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
        }
    }
}
}
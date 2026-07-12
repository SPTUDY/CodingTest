import java.io.*;
import java.util.*;

public class P031 {

    static int v;
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    static int[] distance;

    static class Node {
        int to;     //노드번호
        int cost;   //거리

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        v = Integer.parseInt(br.readLine());
        tree = new ArrayList[v + 1];

        //인접리스트 생성
        for (int i = 1; i <= v; i++) {
            tree[i] = new ArrayList<>();
        }

        //입력 값을 인접리스트에 저장
        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree[from].add(new Node(to, cost));
            }
        }

        //임의의 노드(1)부터 BFS
        BFS(1);

        //1에서부터 가장 멀리 있는 노드를 distance 배열에서 찾아서 far에 저장
        int far = 1;
        for (int i = 2; i <= v; i++) {
            if (distance[i] > distance[far]) far = i;
        }

        //가장 멀리 있는 노드에서부터 BFS
        BFS(far);

        //distance 배열 중 최댓값을 answer에 넣고 출력
        int answer = 0;
        for (int i = 1; i <= v; i++) {
            answer = Math.max(answer, distance[i]);
        }
        System.out.println(answer);
    }

    static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[v + 1];
        distance = new int[v + 1];
        //시작 노드를 방문 처리 및 큐에 추가
        visited[start] = true;
        queue.add(start);

        //큐가 비어있지 않은 동안
        while (!queue.isEmpty()) {
            //큐의 첫번쨰 값을 now에 저장하며 꺼냄
            int now = queue.poll();
            //now와 연결된 노드 중에서 방문하지 않은 노드
            //방문 처리하고 '현재까지의 누적 거리 + 다음에 연결된 노드까지의 거리'를 거리 배열에 저장
            //해당 노드는 추후에 BFS 돌아야 되므로 큐에 추가
            for (Node next : tree[now]) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    distance[next.to] = distance[now] + next.cost;
                    queue.add(next.to);
                }
            }
        }
    }
}
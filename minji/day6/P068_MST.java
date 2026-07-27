import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static int[] parent;
    static int islandNum;
    static ArrayList<ArrayList<int[]>> islandList;
    static boolean[][] visited;
    static ArrayList<int[]> island;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs로 같은 섬끼리 묶기
        islandNum = 1;
        islandList = new ArrayList<>();
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 땅이고 방문한적 없으면
                if(map[i][j] != 0 && !visited[i][j]) {
                    bfs(i,j);
                    islandNum++;
                    islandList.add(island);
                }
            }
        }

        // 섬의 모든 지점에서 만들 수 있는 다리 모두 구하기
        for(int i=0; i<islandList.size(); i++) {
            ArrayList<int[]> now = islandList.get(i);
            for(int j=0; j<now.size(); j++) {
                int x = now.get(j)[0];
                int y = now.get(j)[1];
                int nowIslandNum = map[x][y];

                // 네 방향 검색
                for(int d=0; d<4; d++) {
                    int nx = dx[d];
                    int ny = dy[d];
                    int bridgeLen = 0;

                    while(x + nx>=0 && x + nx<N && y + ny>=0 && y + ny<M) {
                        // 같은 섬이면 다리를 만들 수 없음
                        if(map[x + nx][y + ny] == nowIslandNum) break;
                        // 바다면 다리 길이 +1
                        else if(map[x + nx][y + ny] ==0) bridgeLen++;
                        // 다른 섬이면
                        else {
                            // 다리 길이가 2 이상이면, 다리 엣지에 추가하기
                            if(bridgeLen > 1) {
                                pq.add(new Edge(nowIslandNum, map[x + nx][y + ny], bridgeLen));
                            }
                            break;
                        }

                        // 진행 방향으로 +1
                        if(nx < 0) nx--;
                        else if(nx > 0) nx++;
                        else if(ny < 0) ny--;
                        else ny++;
                    }
                }
            }
        }

        // 다리 길이의 최솟값 구하기
        parent = new int[islandNum];
        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
        }

        int useEdge = 0;
        int answer = 0;
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            // 연결해도 사이클이 생기지 않으면, union 연산 수행
            if(find(now.s) != find(now.e)) {
                union(now.s, now.e);
                answer += now.v; // 엣지의 가중치를 정답 변수에 더하기
                useEdge++;
            }
        }

        // 사용한 엣지 수가 노드 수 - 1이면 가중치 합 출력
        if(useEdge == islandNum - 2) {
            System.out.println(answer);
        }
        else {
            System.out.println(-1);
        }
    }

    // 연결된 섬 찾기
    static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        island = new ArrayList<>();
        int[] start = {i,j};

        q.add(start);
        island.add(start);
        visited[i][j] = true;
        map[i][j] = islandNum;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            for(int d=0; d<4; d++) {
                int nx = dx[d];
                int ny = dy[d];

                while(x+nx>=0 && x+nx<N && y+ny>=0 && y+ny<M) {
                    // 방문한 적 없고, 바다가 아니면 > 같은 섬으로 처리
                    if(!visited[x+nx][y+ny] && map[x+nx][y+ny] != 0) {
                        addNode(x+nx, y+ny, q);
                    }
                    else {
                        break;
                    }

                    if(nx < 0) nx--;
                    else if(nx > 0) nx++;
                    else if(ny < 0) ny--;
                    else ny++;
                }
            }
        }
    }

    public static void addNode(int i, int j, Queue<int[]> q) {
        map[i][j] = islandNum;
        visited[i][j] = true;
        int[] temp = {i,j};
        island.add(temp);
        q.add(temp);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int n) {
        if(n == parent[n]) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static class Edge implements Comparable<Edge> {
        int s, e, v;
        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        public int compareTo(Edge edge) {
            return this.v - edge.v;
        }
    }

}
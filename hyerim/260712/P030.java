import java.io.*;
import java.util.*;

public class P030 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;

    //(dx, dy) = {위, 아래, 왼쪽, 오른쪽}
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                //문자열로 입력이 주어지기 때문에 정수형으로 바꾸려면 -'0' 해줘야됨
                map[i][j] = s.charAt(j) - '0';
            }
        }

        BFS(0, 0);
        //인덱스는 0부터 시작하므로 각각 -1
        System.out.println(map[n - 1][m - 1]);
    }

    static void BFS(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        //큐가 비어있지 않은 동안
        while (!queue.isEmpty()) {
            //현재 제일 앞에 있는 노드(칸) = now
            Node now = queue.poll();

            //현재 칸에서 상하좌우로 검사
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                //미로 밖으로 나가면 continue
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                //만약 갈 수 있는 길이고 (1)
                //아직 방문하지 않은 길이면
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    //방문 처리
                    visited[nx][ny] = true;
                    //map의 값을 depth값으로 업데이트
                    //새로 발견한 길은 현재 길보다 depth가 하나 더 깊어짐
                    map[nx][ny] = map[now.x][now.y] + 1;
                    //BFS 탐색해야되므로 새로 발견한 길을 큐에 넣기
                    queue.add(new Node(nx, ny));
                }
            }
        }
    }
}
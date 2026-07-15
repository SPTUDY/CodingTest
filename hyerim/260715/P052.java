import java.io.*;
import java.util.*;

public class P052 {

    static int[] capacity = new int[3]; //물통의 양
    static boolean[][] visited; //A가 x, B가 y인 상태를 이미 방문했는지 확인
    static boolean[] answer;    //A가 0일 때 가능한 C의 양

    static class State {
        int a, b, c;

        State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        capacity[0] = Integer.parseInt(st.nextToken());
        capacity[1] = Integer.parseInt(st.nextToken());
        capacity[2] = Integer.parseInt(st.nextToken());
        visited = new boolean[capacity[0] + 1][capacity[1] + 1];
        //A=0일때, C에서 iL가 가능하면 answer[i]=1, 붏가능하면 =0
        answer = new boolean[capacity[2] + 1];

        BFS();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= capacity[2]; i++) {
            if (answer[i]) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    static void BFS() {
        Queue<State> queue = new LinkedList<>();

        //처음 상태: A=0, B=0, C=가득참
        queue.add(new State(0, 0, capacity[2]));
        //A=0, B=0인 상태는 방문함
        visited[0][0] = true;
        answer[capacity[2]] = true; //A=0인 현재 C 상태 기록

        while (!queue.isEmpty()) {
            State now = queue.poll();

            int[] water = {now.a, now.b, now.c};
            //i 물통에서 j 물통으로 물 붓기 (6개 경우의 수)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;       //자기 자신에게 물을 붓는 건 무의미
                    int[] next = water.clone(); //물을 본인 외에 두 군데에 부어봐야됨
                    //j 물통에 남은 공간 (더 넣을 수 있는 양)
                    int space = capacity[j] - next[j];
                    //i 물통에서 실제로 옮길 수 있는 양 (i에 남아있는 양과 j 물통 남은 공간 중 최솟값)
                    int move = Math.min(space, next[i]);

                    //물 옮기기
                    next[i] -= move;
                    next[j] += move;

                    //클론하여 저장된 next에서 물 상태를 새로 꺼냄
                    int na = next[0];
                    int nb = next[1];
                    int nc = next[2];

                    //A=na, B=nb인 상태를 방문하지 않았다면 방문 처리
                    if (!visited[na][nb]) {
                        visited[na][nb] = true;
                        queue.add(new State(na, nb, nc));
                        //A 물통이 비어있다면 그떄의 C 물통 양을 정답 후보로 저장
                        if (na == 0) answer[nc] = true;
                    }
                }
            }
        }
    }
}
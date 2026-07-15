import java.io.*;
import java.util.*;

public class Main {
    static int[] capacity = new int[3];
    static boolean[][] visited;
    static boolean[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<3; i++) {
            capacity[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[capacity[0]+1][capacity[1]+1];
        answer = new boolean[capacity[2]+1];

        bfs();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<=capacity[2]; i++) {
            if(answer[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    public static void bfs() {
        Queue<State> q = new LinkedList<>();

        q.add(new State(0, 0, capacity[2]));
        visited[0][0] = true;
        answer[capacity[2]] = true;

        while(!q.isEmpty()) {
            State now = q.poll();

            int[] water = {now.a, now.b, now.c};

            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(i == j) {
                        continue;
                    }

                    int[] next = water.clone();

                    int space = capacity[j] - next[j];
                    int move = Math.min(next[i], space);

                    next[i] -= move;
                    next[j] += move;

                    int a = next[0];
                    int b = next[1];
                    int c = next[2];

                    if(!visited[a][b]) {
                        visited[a][b] = true;
                        q.add(new State(a, b, c));

                        if(a == 0) {
                            answer[c] = true;
                        }
                    }
                }
            }
        }
    }

    static class State {
        int a;
        int b;
        int c;

        State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
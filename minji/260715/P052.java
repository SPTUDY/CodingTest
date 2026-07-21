import java.io.*;
import java.util.*;

public class Main {
    static int[] capacity = new int[3]; // 각각 A, B, C의 최대 용량
    static boolean[][] visited; // visited[a][b] = true
                                // → A에 a리터, B에 b리터가 들어 있는 상태를 이미 확인했다는 뜻
    static boolean[] answer; // answer[c] = true → C에 c리터가 들어 있는 경우가 가능함

    // 물을 옮기는 6가지 경우의 수
    static int[] sender = {0, 0, 1, 1, 2, 2};
    static int[] receiver = {1, 2, 1, 2, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        capacity[0] = sc.nextInt();
        capacity[1] = sc.nextInt();
        capacity[2] = sc.nextInt();

        visited = new boolean[201][201];
        answer = new boolean[201];

        BFS();

        for(int i=0; i<answer.length; i++) {
            if(answer[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS() {
        Queue<State> q = new LinkedList<>();
        q.add(new State(0, 0));
        visited[0][0] = true;
        answer[capacity[2]] = true;

        while(!q.isEmpty()) {
            State now = q.poll();
            int a = now.a;
            int b = now.b;
            int c = capacity[2] - a - b;

            for(int k=0; k<6; k++) {
                int[] next = {a, b, c};
                next[receiver[k]] += next[sender[k]];
                next[sender[k]] = 0;

                // 물이 넘칠 때
                if(next[receiver[k]] > capacity[receiver[k]]) {
                    // 초과하는 만큼 다시 이전 물통에 넣기
                    next[sender[k]] = next[receiver[k]] - capacity[receiver[k]];
                    next[receiver[k]] = capacity[receiver[k]];
                }

                if(!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    q.add(new State(next[0], next[1]));

                    // A의 양이 0이면, C의 무게를 정답 배열에 저장
                    if(next[0] == 0) {
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }

    static class State {
        int a;
        int b;
        public State(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
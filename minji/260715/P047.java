import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] list;
    static long lcm; // 최소공배수
    static boolean[] visited;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        // 입력받기
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt();
        list = new ArrayList[N];
        visited = new boolean[N];
        dist = new long[N];
        lcm = 1;
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }

        // 인접 리스트에 비율 저장하기
        for(int i=0; i<N-1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();

            list[a].add(new Node(b,p,q));
            list[b].add(new Node(a,q,p));

            // 최소공배수 == 두 수의 곱 / 최대공약수
            lcm *= (p*q / gcd(p,q));
        }

        // dfs 수행
        dist[0] = lcm;
        dfs(0);

        long temp = dist[0];
        for(int i=1; i<N; i++) {
            temp = gcd(temp, dist[i]);
        }

        // 정답 출력
        for(int i=0; i<N; i++) {
            System.out.print(dist[i] / temp + " ");
        }
    }

    // 최대공약수 함수
    public static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        else {
            return gcd(b, a%b);
        }
    }

    // dfs 함수
    public static void dfs(int now) {
        visited[now] = true;
        for(Node next : list[now]) {
            int nextB = next.b;
            if(!visited[nextB]) {
                dist[nextB] = dist[now] * next.q / next.p;
                dfs(nextB);
            }
        }
    }

    // Node 클래스
    static class Node {
        int b;
        int p;
        int q;

        public Node(int b, int p, int q) {
            this.b = b;
            this.p = p;
            this.q = q;
        }
    }
}
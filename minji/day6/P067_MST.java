import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s,e,w));
        }

        int useEdge = 0;
        int result = 0;

        while(useEdge < N-1) {
            Edge now = edges.poll();

            // find 연산으로 사이클 생기는지 확인
            if(find(now.start) != find(now.end)) {
                union(now.start, now.end);
                result += now.weight;
                useEdge++;
            }
        }

        System.out.println(result);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }

    public static int find(int n) {
        if(n == parent[n]) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.weight, edge.weight);
        }
    }
}
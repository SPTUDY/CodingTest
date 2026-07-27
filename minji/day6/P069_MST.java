import java.io.*;
import java.util.*;

public class Main {
    static int N, sum = 0;
    static int[] parent;
    static PriorityQueue<Edge> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] ch = st.nextToken().toCharArray();
            for(int j=0; j<N; j++) {
                int temp = 0;
                if(ch[j] >= 'a' && ch[j] <='z') {
                    temp = ch[j] - 'a' + 1;
                }
                else {
                    temp = ch[j] - 'A' + 27;
                }
                sum += temp; // 총 랜선 길이
                if(i != j && temp != 0) {
                    q.add(new Edge(i, j, temp));
                }
            }
        }

        parent = new int[N];
        for(int i=0; i<N; i++) {
            parent[i] = i;
        }

        int useEdge = 0;
        int answer = 0;
        while(!q.isEmpty()) {
            Edge now = q.poll();
            if(find(now.s) != find(now.e)) {
                union(now.s, now.e);
                answer += now.v;
                useEdge++;
            }
        }

        if(useEdge == N-1) {
            System.out.println(sum - answer);
        }
        else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }

    public static int find(int n) {
        if(n == parent[n]) return n;
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
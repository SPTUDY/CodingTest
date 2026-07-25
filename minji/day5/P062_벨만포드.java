import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Edge[] edges;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 초기화
        edges = new Edge[M+1];
        dist = new int[N+1];
        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // 엣지 입력받기
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s,e,w);
        }

        // N-1번 엣지 확인
        dist[1] = 0;
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M; j++) {
                Edge edge = edges[j];
                if(dist[edge.s] != Integer.MAX_VALUE
                    && dist[edge.e] > dist[edge.s] + edge.w) {
                    dist[edge.e] = dist[edge.s] + edge.w;
                }
            }
        }

        // 음수 사이클 유무 확인
        boolean isNegative = false;
        for(int j=0; j<M; j++) {
            Edge edge = edges[j];
            if(dist[edge.s] != Integer.MAX_VALUE
                    && dist[edge.e] > dist[edge.s] + edge.w) {
                isNegative = true;
            }
        }

        // 결과 출력
        if(isNegative) {
            System.out.println("-1");
        }
        else {
            for(int i=2; i<=N; i++) {
                if(dist[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                }
                else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    static class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
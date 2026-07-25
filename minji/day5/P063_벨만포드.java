import java.io.*;
import java.util.*;

public class Main {
    static int N, S, E, M;
    static Edge[] edges;
    static int[] dist;
    static int[] cityMoney;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 초기화
        edges = new Edge[M];
        dist = new int[N];
        cityMoney = new int[N];
        for(int i=0; i<N; i++) {
            dist[i] = Integer.MIN_VALUE;
        }

        // 엣지 입력받기
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s,e,p);
        }

        // 도시에서 벌 수 있는 돈
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cityMoney[i] = Integer.parseInt(st.nextToken());
        }

        // 변형 벨만 포드 수행 (양수 사이클 확인)
        dist[S] = cityMoney[S];
        for(int i=0; i<=N+100; i++) {
            for(int j=0; j<M; j++) {
                int s = edges[j].s;
                int e = edges[j].e;
                int price = edges[j].price;

                // 출발 노드가 방문하지 않은 노드면 스킵
                if(dist[s] == Integer.MIN_VALUE) {
                    continue;
                }
                // 시작 노드가 양수 사이클이면 종료 사이클도 양수 사이클로 업뎃
                else if (dist[s] == Integer.MAX_VALUE) {
                    dist[e] = Integer.MAX_VALUE;
                }
                // 최대 비용으로 업데이트
                else if(dist[e] < dist[s] + cityMoney[e] - price) {
                    dist[e] = dist[s] + cityMoney[e] - price;
                    if(i >= N-1) { // N-1번 반복 이후 업데이트 되는 노드면 양수 사이클로 처리
                        dist[e] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        // 결과 출력
        if(dist[E] == Integer.MIN_VALUE) { // 도착 도시 도달 불가능
            System.out.println("gg");
        }
        else if(dist[E] == Integer.MAX_VALUE) { // 무한히 벌 수 있음
            System.out.println("Gee");
        }
        else { // 최대 비용 출력
            System.out.println(dist[E]);
        }
    }

    static class Edge {
        int s;
        int e;
        int price;
        public Edge(int s, int e, int price) {
            this.s = s;
            this.e = e;
            this.price = price;
        }
    }
}
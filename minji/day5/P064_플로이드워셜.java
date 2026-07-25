import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            dist[s][e] = Math.min(dist[s][e], v);
        }

        // 플로이드워셜
        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    dist[i][j] = Math.min(dist[i][j],
                                          dist[i][k] + dist[k][j]);
                }
            }
        }

        // 결과 출력
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("0 ");
                }
                else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

    }
}
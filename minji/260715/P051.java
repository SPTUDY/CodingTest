import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] check;
    static boolean isBi = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 노드
            int E = Integer.parseInt(st.nextToken()); // 엣지

            list = new ArrayList[V+1];
            for(int i=1; i<=V; i++) {
                list[i] = new ArrayList<>();
            }

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            visited = new boolean[V+1];
            check = new int[V+1];

            for(int i=1; i<=V; i++) {
                if(isBi) {
                    DFS(i);
                }
                else {
                    break;
                }
            }

            if(isBi) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void DFS(int now) {
        visited[now] = true;
        for(int next : list[now]) {
            if(!visited[next]) {
                check[next] = (check[now] + 1) % 2;
                DFS(next);
            }
            else if(check[next] == check[now]) {
                isBi = false;
            }
        }
    }
}
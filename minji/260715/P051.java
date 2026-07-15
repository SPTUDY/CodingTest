import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
    static int[] visited;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        for(int t=0; t<K; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V+1];
            visited = new int[V+1];
            check = true;

            for(int i=0; i<=V; i++) {
                list[i] = new ArrayList<>();
            }

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                list[s].add(e);
                list[e].add(s);
            }

            for(int i=1; i<=V; i++) {
                if(visited[i] == 0) {
                    visited[i] = 1;
                    dfs(i);
                }

                if(!check) {
                    break;
                }
            }

            if(check) {
                sb.append("YES").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
    }

    public static void dfs(int now) {
        for(int next : list[now]) {
            if(visited[next] == 0) {
                visited[next] = -visited[now];
                dfs(next);
            }
            else if(visited[next] == visited[now]) {
                check = false;
                return;
            }

            if(!check) {
                return;
            }
        }
    }
}
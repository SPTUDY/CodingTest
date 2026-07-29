import java.io.*;
import java.util.*;

public class Main {
    static int N, root = 0, deleteNode, answer = 0;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        tree = new ArrayList[N+1];
        for(int i=0; i<N; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n == -1) {
                root = i;
            }
            else {
                tree[i].add(n);
                tree[n].add(i);
            }
        }

        deleteNode = Integer.parseInt(br.readLine());
        if(deleteNode == root) {
            System.out.println(0);
        }
        else {
            dfs(root);
            System.out.println(answer);
        }
    }

    static void dfs(int now) {
        visited[now] = true;
        int child = 0;
        for(int next : tree[now]) {
            if(!visited[next] && next != deleteNode) {
                child++;
                dfs(next);
            }
        }

        // 리프노드
        if(child == 0) {
            answer++;
        }
    }
}
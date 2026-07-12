import java.io.*;
import java.util.*;

public class P026 {
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];

        //수열의 길이는 0부터 시작
        backtracking(0);
        System.out.println(sb);
    }

    static void backtracking(int len) {
        //수열의 길이를 만족하면 StringBuilder에 값 추가
        if (len == m) {
            for (int i = 0; i < m; i++) {
                //arr에 0부터 추가되기 때문에 +1해줘서 1부터 시작되는걸로 바꿈
                sb.append(arr[i] + 1).append(' ');
            }
            sb.append('\n');
            return;
        }

        //1부터 n까지 반복
        for (int i = 0; i < n; i++) {
            //미방문 노드이면
            //방문했다고 바꿔주고 해당 원소를 arr에 추가
            if (!visited[i]) {
                visited[i] = true;
                arr[len] = i;
                //DFS 재귀 실행하며 수열의 길이 ++
                backtracking(len + 1);
                //재귀 실행을 하다가 return되면 이곳으로 되돌아옴
                //false로 바꾸어 다른 수열에 포함될 수 있도록 함
                visited[i] = false;
            }
        }
    }
}
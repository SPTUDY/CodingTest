import java.io.*;
import java.util.*;

public class P023 {
    static boolean visited[];
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        //인접리스트 구현
        //ArrayList를 담을 배열만 생성됨
        arr = new ArrayList[n + 1];
        //처음 노드에 각각 ArrayList 연결
        for (int i = 1; i < n + 1; i++) {
            arr[i] = new ArrayList<Integer>();
        }
        //연결된 ArrayList에 노드 넣기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            //방향이 없기 때문에 s<->e 서로 추가
            arr[s].add(e);
            arr[e].add(s);
        }
        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            //방문하지 않은 노드가 있으면
            if (!visited[i]) {
                //DFS 횟수를 추가하고
                //방문하지 않은 노드부터 DFS 실행
                answer++;
                DFS(i);
            }
        }
        System.out.println(answer);
    }

    private static void DFS(int v) {
        //만약 방문한 노드라면 함수 종료
        if (visited[v]) return;
        //만약 방문하지 않은 노드라면
        visited[v] = true;
        //현재 노드에 연결된 노드들을 탐색
        for (int i : arr[v]) {
            //현재 노드에 연결된 노드가 탐색되지 않았으면
            //DFS 재귀적 수행
            if (!visited[i]) DFS(i);
        }
    }


}
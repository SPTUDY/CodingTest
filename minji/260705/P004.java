import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 2차원 배열의 크기
        int m = Integer.parseInt(st.nextToken()); // 구간합 질의 개수

        int[][] arr = new int[n+1][n+1];
        int[][] s = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                s[i][j] = arr[i][j] + s[i-1][j] + s[i][j-1] - s[i-1][j-1];
            }
        }

        for(int t=0; t<m; t++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            System.out.println(s[x2][y2] - s[x2][y1-1] - s[x1-1][y2] + s[x1-1][y1-1]);
        }

    }
}

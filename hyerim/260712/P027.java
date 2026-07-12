import java.io.*;

public class P027 {
    static int n;
    static int[] queen;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        queen = new int[n];
        //0번째 행부터 퀸 놓기 시작
        NQueen(0);
        System.out.println(answer);
    }

    static void NQueen(int row) {
        //재귀 n까지 돌면 조건 만족했으므로 경우의 수++
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            //row번째 행에 있는 퀸은 col번째 열에 있음
            //인덱스는 퀸의 행, 값은 퀸의 열
            //예를 들어 queen[0]=1이면 퀸이 (0,1)에 위치
            queen[row] = col;
            if (check(row)) NQueen(row + 1);
        }
    }

    static boolean check(int row) {
        //현재 위치한 행보다 위에 있는 행만 검사
        //아래 행에는 아직 퀸 배치 안함
        for (int i = 0; i < row; i++) {
            //같은 열이면 공격 가능
            //즉, i행에 위치한 값과 row행에 위치한 값이 같으면 row열에 이미 퀸 배치됨
            if (queen[i] == queen[row]) return false;
            //같은 대각선이면 공격 가능
            //인덱스 차이=값의 차이면 행 차이=열 차이이므로 같은 대각선에 위치한다는 의미
            if (Math.abs(row - i) == Math.abs(queen[row] - queen[i])) return false;
        }
        return true;    //직선공격, 대각선공격 모두 return 되지 않았을 때
    }
}
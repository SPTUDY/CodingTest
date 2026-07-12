import java.io.*;
import java.util.StringTokenizer;

public class P028 {
    static int[][] map = new int[10][10];
    static int[] paper = new int[6];    //색종이 1*1 ~ 5*5
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0, 0);
        //만약 정수 최댓값이 갱신되지 않았으면 조건 내에서 색종이 붙이기 불가능하므로 -1 출력
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);

    }

    static void backtracking(int row, int col, int count) {
        //이미 현재 count가 기존 정답보다 크거나 같은 경우,
        //더이상 볼 필요 없음 (가지치기)
        if (count >= answer) return;
        //한 행을 다 봤으면 다음 행으로 이동
        //열은 0으로 초기화
        if (col == 10) {
            backtracking(row + 1, 0, count);
            return;
        }
        //행을 모두 다 봤으면 색종이 붙이기 완료
        //현재 정답과 이번 count 중 최소값으로 answer 업데이트
        if (row == 10) {
            answer = Math.min(answer, count);
            return;
        }
        //현재 칸이 0이면 다음 칸으로 이동
        if (map[row][col] == 0) {
            backtracking(row, col + 1, count);
            return;
        }

        //제일 큰 색종이부터 체크
        for (int size = 5; size >= 1; size--) {
            //해당 크기의 색종이를 5개 미만으로 사용했고,
            //해당 크기의 색종이를 붙여도 map을 벗어나지 않으며 모두 1로 구성되어 있어서 붙일 수 있는 경우
            if (paper[size] < 5 && check(row, col, size)) {
                //색종이 붙이고 값을 0으로 바꾸기
                attach(row, col, size, 0);
                //해당 크기 색종이 사용 개수 증가
                paper[size]++;

                //색종이 붙이고 나서 다음 칸 탐색
                //색종이 하나 사용했으므로 count++
                backtracking(row, col + 1, count + 1);

                //색종이 떼고 값을 1로 바꾸기
                attach(row, col, size, 1);
                //해당 크기 색종이 사용 개수 감소
                paper[size]--;
            }
        }
    }

    static boolean check(int row, int col, int size) {
        //만약 현재 칸에서 색종이 붙여서 10 넘어가면
        //map을 벗어나기 때문에 불가능
        if (row + size > 10 || col + size > 10) return false;

        //map[i][j]부터 색종이 크기만큼 순회하면서
        //0이 등장하면 해당 크기만큼의 색종이를 붙일 수 없으므로 false 리턴
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;    //위 조건에 걸리지 않으면 가능
    }

    static void attach(int row, int col, int size, int value) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                map[i][j] = value;
            }
        }
    }
}
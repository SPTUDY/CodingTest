import java.io.*;
import java.util.*;

public class P009 {
    static int statusArr[];
    static int checkArr[];
    static int checkAnswer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = 0;
        char[] stringArr = new char[n]; //문자열 담을 배열
        checkArr = new int[4];    //목표 acgt 개수 담을 배열
        statusArr = new int[4];   //현재 부분 문자열에서의 acgt 개수 담을 배열
        checkAnswer = 0;          //acgt가 만족 되면 ++, 4 되면 answer++
        stringArr = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            //만약 필요 개수가 0이면 이미 조건을 만족한 상태
            if (checkArr[i] == 0) {
                checkAnswer++;
            }
        }
        //첫번째 윈도우
        for (int i = 0; i < m; i++) {
            Add(stringArr[i]);
        }
        if (checkAnswer == 4) answer++;

        //슬라이딩 윈도우
        //i는 윈도우의 맨 끝 문자
        for (int i = m; i < n; i++) {
            //j는 윈도우의 맨 앞 문자
            int j = i - m;
            Add(stringArr[i]);
            Remove(stringArr[j]);
            if (checkAnswer == 4) answer++;
        }

        System.out.println(answer);
    }

    //입력 문자열을 부분 문자열로 넣기
    //부분 문자열의 acgt 개수와 목표 acgt 개수가 '같은 경우'에 checkAnswer++
    private static void Add(char c) {
        switch (c) {
            case 'A':
                //부분문자열 a 개수 증가
                statusArr[0]++;
                //부분문자열 a 개수와 목표 a 개수가 같으면 checkAnswer++
                if (statusArr[0] == checkArr[0]) checkAnswer++;
                break;
            case 'C':
                statusArr[1]++;
                if (statusArr[1] == checkArr[1]) checkAnswer++;
                break;
            case 'G':
                statusArr[2]++;
                if (statusArr[2] == checkArr[2]) checkAnswer++;
                break;
            case 'T':
                statusArr[3]++;
                if (statusArr[3] == checkArr[3]) checkAnswer++;
                break;
        }
    }

    //윈도우 가장 앞부분(j)을 제거하는 과정
    private static void Remove(char c) {
        switch (c) {
            case 'A':
                //부분문자열 a 개수와 목표 a 개수가 같은 경우에 checkAnswer--
                //윈도우에서 해당 문자를 제거하는 과정이기 때문에 정답을 만족하지 않게 되므로 --
                if (statusArr[0] == checkArr[0]) checkAnswer--;
                //부분문자열 a 개수 감소: 윈도우에서 빠지기 때문
                statusArr[0]--;
                break;
            case 'C':
                if (statusArr[1] == checkArr[1]) checkAnswer--;
                statusArr[1]--;
                break;
            case 'G':
                if (statusArr[2] == checkArr[2]) checkAnswer--;
                statusArr[2]--;
                break;
            case 'T':
                if (statusArr[3] == checkArr[3]) checkAnswer--;
                statusArr[3]--;
                break;
        }
    }
}
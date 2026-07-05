import java.util.*;
import java.io.*;

public class Main {
    static int[] minAlpha = new int[4];
    static int[] current = new int[4];
    static char[] DNA;
    static int checkSecret;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
        DNA = br.readLine().toCharArray();
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            minAlpha[i] = Integer.parseInt(st.nextToken());
            if(minAlpha[i] == 0) {
                checkSecret++;
            }
        }

        // 초기 부분 문자열 처리
        for(int i=0; i<P; i++) {
            add(DNA[i]);
        }
        if(checkSecret == 4) {
            answer++;
        }

        // 슬라이딩 윈도우
        for(int i=P; i<S; i++) {
            int j = i - P; // 앞쪽 인덱스 체크

            add(DNA[i]);
            remove(DNA[j]);

            if(checkSecret == 4) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    // 새로 들어온 문자 처리
    private static void add(char c) {
        switch (c) {
            case 'A':
                current[0]++;
                if(minAlpha[0] == current[0]) checkSecret++;
                break;
            case 'C':
                current[1]++;
                if(minAlpha[1] == current[1]) checkSecret++;
                break;
            case 'G':
                current[2]++;
                if(minAlpha[2] == current[2]) checkSecret++;
                break;
            case 'T':
                current[3]++;
                if(minAlpha[3] == current[3]) checkSecret++;
                break;
        }
    }

    private static void remove(char c) {
        switch (c) {
            case 'A':
                if(minAlpha[0] == current[0]) checkSecret--;
                current[0]--;
                break;
            case 'C':
                if(minAlpha[1] == current[1]) checkSecret--;
                current[1]--;
                break;
            case 'G':
                if(minAlpha[2] == current[2]) checkSecret--;
                current[2]--;
                break;
            case 'T':
                if(minAlpha[3] == current[3]) checkSecret--;
                current[3]--;
                break;
        }
    }
}

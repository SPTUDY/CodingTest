import java.io.*;

public class P039 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //- 연산 뒤의 더하기끼리 괄호로 묶어 전부 빼면 최솟값이 됨
        //-를 기준으로 split
        String input = br.readLine();
        String[] minusSplit = input.split("-");

        //처음 값 = minusSplit[0]
        int answer = sum(minusSplit[0]);

        for (int i = 1; i < minusSplit.length; i++) {
            answer -= sum(minusSplit[i]);
        }

        System.out.println(answer);
    }

    //괄호 안 더하기
    static int sum(String str) {
        //문자열을 + 기준으로 나눔
        String[] plusSplit = str.split("\\+");

        //각 숫자를 정수로 바꾸고 모두 더함
        int total = 0;
        for (int i = 0; i < plusSplit.length; i++) {
            total += Integer.parseInt(plusSplit[i]);
        }

        return total;
    }
}
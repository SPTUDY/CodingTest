import java.io.*;
import java.util.*;

public class P037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        int one = 0;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 1) pos.add(num);
            else if (num == 1) one++;
            else if (num == 0) zero++;
            else neg.add(num);

        }

        //양수는 내림차순 정렬
        Collections.sort(pos, Collections.reverseOrder());
        //음수는 오름차순 정렬
        Collections.sort(neg);

        int answer = 0;

        //2 이상의 양수는 큰 수끼리 곱하기
        //2개의 숫자를 곱하기 때문에 뒤에 곱해지는 숫자(i+1)가 양수 배열에 포함되는지 검사
        //2씩 증가
        for (int i = 0; i < pos.size(); i += 2) {
            if (i + 1 < pos.size()) answer += pos.get(i) * pos.get(i + 1);
            else answer += pos.get(i);
        }

        //1은 곱하지 않고 그냥 더하기
        answer += one;

        //음수는 제일 작은 수끼리 곱하기-> 양수로 만듦
        //2개의 숫자를 곱하기 때문에 뒤에 곱해지는 숫자(i+1)가 음수 배열에 포함되는지 검사
        //2씩 증가
        for (int i = 0; i < neg.size(); i += 2) {
            if (i + 1 < neg.size()) {
                answer += neg.get(i) * neg.get(i + 1);
            } else {
                //음수가 하나 남았는데 0이 없으면 더해야 함
                if (zero == 0) answer += neg.get(i);
            }
        }

        System.out.println(answer);
    }
}
import java.io.*;
import java.util.*;

public class P012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] answer = new int[n];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        Stack<Integer> stack = new Stack<>();
        //인덱스를 스택에 넣을 예정 -> 오큰수를 못찾은 인덱스만 스택에 들어감
        //처음에는 0 넣기
        stack.push(0);
        for (int i = 1; i < n; i++) {
            //스택이 비어있지 않고
            //현재 arr 값이 스택의 탑 값 인덱스가 가리키는 arr보다 클 경우
            //즉, 오큰수를 못찾은 값이 오큰수를 찾게 되었을 때
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                //해당 인덱스에 오큰수 저장하고
                //오큰수를 찾았기 때문에 스택에서 제거
                answer[stack.pop()] = arr[i];
            }
            //다음 인덱스를 스택에 넣음
            stack.push(i);
        }
        //오큰수 찾는 과정이 다 끝났는데
        //스택이 비어있지 않다면 오큰수를 못찾은 것
        while (!stack.isEmpty()) {
            //answer 배열에 -1 넣어줌
            answer[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(answer[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
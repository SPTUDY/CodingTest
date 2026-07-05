import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] A = new int[n];
        for(int i=0; i<n; i++) {
            A[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        int num = 1;
        boolean answer = true;
        StringBuffer bf = new StringBuffer();

        for(int i=0; i<A.length; i++) {
            int now = A[i];

            // 현재 값이 수열에 있는 수보다 큰 경우
            if(now >= num) {
                while(now >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            }
            // 작은 경우 -> 출력 불가
            else {
                int top = stack.pop();
                if(top > now) {
                    System.out.println("NO");
                    answer = false;
                    break;
                }
                else {
                    bf.append("-\n");
                }
            }
        }

        if(answer) {
            System.out.println(bf);
        }
    }
}

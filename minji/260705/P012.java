import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = sc.nextInt();
        }

        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i=1; i<N; i++) {
            while(!stack.isEmpty() && A[stack.peek()] < A[i]) {
                answer[stack.pop()] = A[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        for(int i=0; i<N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}

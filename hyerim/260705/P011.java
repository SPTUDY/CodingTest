import java.util.*;

//arr[i]가 현재 자연수보다 크거나 같으면
//자연수부터 arr[i]까지 push하고 arr[i]==자연수 됐을 때 pop
//arr[i]가 현재 자연수보다 작으면
//stack top을 pop해서 arr[i]와 같은지 비교
//같으면 - 추가, 다르면 NO 출력 후 종료
public class P011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        boolean result = true;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            //arr에서 꺼낸 값
            int s = arr[i];
            //만약 arr에서 꺼낸 값(=목표값)이 자연수보다 크거나 같으면
            if (s >= num) {
                //자연수가 목표값과 같아질 때까지
                //스택에 push하며 ++
                while (s >= num) {
                    stack.push(num++);
                    sb.append("+\n");
                }
                //목표값까지 push했으므로 stack top에는 목표값이 된 자연수가 있음
                //stack top pop하고 - 추가
                stack.pop();
                sb.append("-\n");
            } else {
                //만약 목표값이 stack top pop보다 작으면 자연수 pop
                int m = stack.pop();
                //pop된 자연수가 목표값과 다르면 NO 출력하고 종료
                if (m != s) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    //만약 pop된 자연수가 목표값과 같으면 - 추가
                    sb.append("-\n");
                }
            }
        }
        if (result) System.out.println(sb.toString());

    }


}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] slot = new int[N][2];
        for(int i=0; i<N; i++) {
            slot[i][0] = sc.nextInt();
            slot[i][1] = sc.nextInt();
        }
        
        Arrays.sort(slot, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                // 종료 시간이 같을 때
                if(a[1] == b[1]) {
                    return a[0] - b[0]; // 시작시간 오름차순
                }
                return a[1] - b[1];
            }
        });
        
        int answer = 0;
        int end = -1;
        for(int i=0; i<N; i++) {
            if(slot[i][0] >= end) {
                end = slot[i][1];
                answer++;
            }
        }

        System.out.println(answer);
    }
}
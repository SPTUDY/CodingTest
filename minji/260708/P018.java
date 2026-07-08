import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        // 삽입 정렬 (오름차)
        for(int i=1; i<n; i++) {
            // 현재 확인하는 데이터: i번째 데이터 arr[i]
            int insert_point = i;
            int insert_value = arr[i];

            // 0 ~ i-1 까지 돌면서 삽입할 위치 찾기
            for(int j=i-1; j>=0; j--) {
                // j번째 데이터가 확인 데이터보다 작으면? 그 다음 인덱스가 삽입 포인트
                if(arr[j] < arr[i]) {
                    insert_point = j+1;
                    break;
                }
                // 0번째까지 다 확인했으면 0번째에 삽입 (현재 확인 데이터가 최솟값임)
                if(j==0) {
                    insert_point = 0;
                }
            }

            // shift 실행 (
            for(int j=i; j>insert_point; j--) {
                arr[j] = arr[j-1];
            }
            arr[insert_point] = insert_value;
        }

        // 합배열 활용
        int[] s = new int[n];
        s[0] = arr[0];
        for(int i=1; i<n; i++) {
            s[i] = s[i-1] + arr[i];
        }

        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += s[i];
        }
        System.out.println(sum);
    }
}
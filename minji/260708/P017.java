import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int[] arr = new int[s.length()];
        for(int i=0; i<s.length(); i++) {
            arr[i] = Integer.parseInt(s.substring(i, i+1));
        }

        // 선택 정렬 (내림차)
        for(int i=0; i<s.length(); i++) {
            int max = i;
            for(int j=i+1; j<s.length(); j++) {
                if(arr[j] > arr[max]) {
                    max = j;
                }
            }

            if(arr[max] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }

        for(int i=0; i<s.length(); i++) {
            System.out.print(arr[i]);
        }
    }
}
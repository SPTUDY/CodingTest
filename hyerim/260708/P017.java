import java.util.*;

public class P00 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();

        int[] arr = new int[n.length()];

        for (int i = 0; i < n.length(); i++) {
            arr[i] = Integer.parseInt(n.substring(i, i + 1));
        }

        for (int i = 0; i < arr.length; i++) {
            int max = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[max] < arr[j]) {
                    max = j;
                }
            }

            if (arr[i] < arr[max]) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}
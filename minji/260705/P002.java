import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int max = Integer.MIN_VALUE;
        int[] score = new int[n];
        for(int i=0; i<n; i++) {
            score[i] = sc.nextInt();
            if(max < score[i]) {
                max = score[i];
            }
        }

        double sum = 0;
        for(int i=0; i<n; i++) {
            sum += (double) score[i] / max * 100;
        }

        System.out.println(sum/n);
    }
}

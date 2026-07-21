import java.util.*;

public class Main {
    public static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        parent = new int[N+1];
        for(int i=0; i<=N; i++) {
            parent[i] = i;
        }

        for(int i=0; i<M; i++) {
            int question = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(question == 0) {
                union(a,b);
            }
            else {
                a = find(a);
                b = find(b);

                if(checkSame(a,b)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }

    private static int find(int n) {
        if(n == parent[n]) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    private static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return true;
        else return false;
    }
}
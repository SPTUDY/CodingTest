import java.io.*;
import java.util.*;

public class P047 {
    static int n;
    static ArrayList<Node>[] list;
    static boolean[] visited;
    //각 재료의 양을 저장하는 배열
    static long[] amount;

    static class Node {
        int to; //연결된 재료 번호
        int p;  //현재 재료의 비율
        int q;  //연결된 재료의 비율

        Node(int to, int p, int q) {
            this.to = to;
            this.p = p;
            this.q = q;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n];
        visited = new boolean[n];
        amount = new long[n];

        //인접리스트 만들기
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        //0번 재료의 시작 양을 정하기 위한 값
        //처음부터 충분히 큰 수로 시작해야 나머지 값들이 정수값이 나오게 될 수 있음
        //나중에 gcd로 나눠서 최소 정수비로 만들면 됨
        long lcm = 1;
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            //인접리스트가 무방향이므로 양쪽노드에 모두 추가
            //a의 양을 알고 있다면, a:b=p:q이므로 b=a*q/p
            //b의 양을 알고 있다면, a=b*p/q
            list[a].add(new Node(b, p, q));
            list[b].add(new Node(a, q, p));

            //비율의 최대공약수 구하기
            //p와 q의 최소공배수를 lcm에 곱함
            //amount[0]을 모든 값의 최소공배수로 시작하면 나머지 재료를 정수값으로 만들기 쉬움
            long gcd = gcd(p, q);
            lcm *= (p / gcd) * q;
        }

        //0번 재료의 값을 충분히 큰 값으로 설정
        //모든 재료의 양을 계산한 뒤 모든 재료의 최대공약수로 나눠주면 최소 정수비가 됨
        amount[0] = lcm;
        //0번 재료부터 연결된 재료의 양을 계산
        DFS(0);
        //모든 재료의 양을 구하고 나면 gcd를 0번 값으로 설정
        long resultGCD = amount[0];

        //모든 재료의 최대공약수를 gcd 함수로 구하기
        //resultGCD에는 최대공약수의 값이 담김
        for (int i = 1; i < n; i++) {
            resultGCD = gcd(resultGCD, amount[i]);
        }
        //gcd로 나눠서 가장 작은 정수 비 출력
        for (int i = 0; i < n; i++) {
            System.out.print(amount[i] / resultGCD + " ");
        }
    }

    static void DFS(int now) {
        visited[now] = true;
        for (Node next : list[now]) {
            if (!visited[next.to]) {
                //현재 재료의 양을 알고 있을 때 다음 연결된 재료 양 계산
                amount[next.to] = amount[now] * next.q / next.p;
                DFS(next.to);
            }
        }
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
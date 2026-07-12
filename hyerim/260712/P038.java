import java.io.*;
import java.util.*;

public class P038 {

    static class Meeting {
        int start;
        int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(start, end);
        }

        //끝나는 시간 기준 오름차순 정렬
        Arrays.sort(meetings, (o1, o2) -> {
            //두개의 미팅의 끝나는 시간이 같다면
            //시작 시간 기준 오름차순 정렬
            //결과가 음수면 o1<o2이므로 o1의 시작시간이 더 빨라서 앞에 옴
            if (o1.end == o2.end) return o1.start - o2.start;
            //결과가 음수면 o1<o2이므로 o1의 끝시간이 더 빨라서 앞에 옴
            return o1.end - o2.end;
        });

        int answer = 0;
        int endTime = 0;    //마지막으로 선택한 회의가 끝나는 시간

        for (int i = 0; i < n; i++) {
            //현재 회의의 시작 시간이 이전 회의의 끝나는 시간보다 늦거나 같으면 선택 가능
            if (meetings[i].start >= endTime) {
                answer++;
                endTime += meetings[i].end;
            }
        }
        System.out.println(answer);

    }
}
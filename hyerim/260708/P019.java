import java.util.*;

public class P019 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        //실제 인덱스는 0부터 시작하므로 k번째 수를 구하기 위해서는 k-1 인덱스에 위치한 값이 필요
        quickSort(arr, 0, n - 1, k - 1);
        System.out.println(arr[k - 1]);
    }

    public static void quickSort(int[] arr, int s, int e, int k) {
        if (s < e) {
            int pivot = partition(arr, s, e);
            //k가 pivot이면 더이상 구할 필요 없음
            if (pivot == k) return;
                //k가 pivot보다 작으면 왼쪽 그룹만 정렬 수행
            else if (k < pivot) quickSort(arr, s, pivot - 1, k);
                //k가 pivot보다 크면 오른쪽 그룹만 정렬 수행
            else quickSort(arr, pivot + 1, e, k);
        }
    }

    public static int partition(int[] arr, int s, int e) {
        //파티션에서 원소가 두개 남았을 때
        if (s + 1 == e) {
            if (arr[s] > arr[e]) swap(arr, s, e);
            return e;
        }
        //가운데 값을 pivot으로 잡고 제일 앞 위치로 옮김
        //제일 앞 위치랑 swap
        int m = (s + e) / 2;
        swap(arr, s, m);
        //제일 앞 원소(원래 가운데 값)를 pivot으로 설정
        int pivot = arr[s];
        //시작 다음 값 ~ 끝
        int i = s + 1, j = e;
        //i++, j--되면서 i가 j를 지나치기 전까지 반복
        while (i <= j) {
            //pivot값보다 크고, s+1보다 큰 동안
            //arr[j]가 pivot보다 작은 값이면 j는 거기서 멈춤
            while (j >= s + 1 && pivot < arr[j]) j--;
            //pivot값보다 작고, e보다 작은 동안
            //arr[i]가 pivot보다 큰 값이면 i는 거기서 멈춤
            while (i <= e && pivot > arr[i]) i++;
            //i와 j가 각각 멈춰있고, i가 j보다 더 작으면 둘 자리를 바꿈
            //그러고 i++, j--
            if (i <= j) swap(arr, i++, j--);
        }
        //pivot을 최종 위치 j에 넣음: while(i<=j)이므로 i는 j를 지나친 상태에서 종료됨
        //따라서 j가 최종 위치 됨
        //j 왼쪽에는 pivot보다 작은 값들
        //j 오른쪽에는 pivot보다 큰 값들
        arr[s] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_POS = 100001;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            System.out.println(n - k);
            return;
        }

        int[] time = new int[MAX_POS];
        Arrays.fill(time, MAX_POS);

        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        heap.add(new int[]{0, n});
        while (!heap.isEmpty()) {
            int[] now = heap.poll();
            for (int next : List.of(now[1] - 1, now[1] + 1)) {
                if (-1 < next && next < MAX_POS) {
                    if (now[0] + 1 < time[next]) {
                        time[next] = now[0] + 1;
                        heap.add(new int[]{now[0] + 1, next});
                    }
                }
            }
            if (now[1] * 2 < MAX_POS && now[0] < time[now[1] * 2]) {
                heap.add(new int[]{now[0], now[1] * 2});
                time[now[1] * 2] = now[0];
            }
        }
        System.out.println(time[k]);

    }
}
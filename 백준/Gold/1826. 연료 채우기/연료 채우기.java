import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] station = new int[n][];
        for (int i = 0; i < n; i++) {
            String[] inputLine = br.readLine().split(" ");
            station[i] = new int[]{Integer.parseInt(inputLine[0]), Integer.parseInt(inputLine[1])};
        }
        Arrays.sort(station, (o1, o2) -> o1[0] - o2[0]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if (p >= l) {
            System.out.println(0);
            return;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o2[1] == o1[1]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        int pos = p;

        for (int i = 0; i < n; i++) {
            if (station[i][0] <= pos) {
                pq.add(Arrays.copyOf(station[i], 2));
            }
        }
        int idx = pq.size();
        int cnt = 0;
        while (!pq.isEmpty()) {
//            System.out.println(pos + " " + idx);
//            for (int[] ints : pq) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println();

            pos += pq.poll()[1];
            cnt++;
            if (pos >= l) {
                System.out.println(cnt);
                return;
            }
            while (idx < n && station[idx][0] <= pos) {
                pq.add(Arrays.copyOf(station[idx++], 2));
            }
        }
        System.out.println(-1);

    }
}
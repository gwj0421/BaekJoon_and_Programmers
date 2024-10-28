import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] origin = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{origin[i], i});
        }

        for (int last = n - 1; last > -1; last--) {
            while (!pq.isEmpty() && pq.peek()[1] > last) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] i = pq.poll();
//            System.out.println(Arrays.toString(i));
            if (last != i[1]) {
                if (--k == 0) {
                    StringBuilder sb = new StringBuilder();
                    if (i[0] < origin[last]) {
                        sb.append(i[0]).append(" ").append(origin[last]);
                    } else {
                        sb.append(origin[last]).append(" ").append(i[0]);
                    }
                    System.out.println(sb);
                    return;
                }
                origin[i[1]] = origin[last];
                pq.add(new int[]{origin[last], i[1]});
            }
        }
        System.out.println(-1);
    }

}
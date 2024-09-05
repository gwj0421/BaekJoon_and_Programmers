import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] edges = new int[n][];
        int hi, oi;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            hi = Integer.parseInt(st.nextToken());
            oi = Integer.parseInt(st.nextToken());
            if (hi > oi) {
                edges[i] = new int[]{oi, hi};
            } else {
                edges[i] = new int[]{hi, oi};
            }
        }
        int d = Integer.parseInt(br.readLine());
        Arrays.sort(edges, Comparator.comparingInt(o -> o[1]));


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxVal = 0;
        for (int[] edge : edges) {
            if (edge[1] - d <= edge[0]) {
                pq.add(edge[0]);
            }
            while (!pq.isEmpty() && pq.peek() < edge[1] - d) {
                pq.poll();
            }
            maxVal = Math.max(maxVal, pq.size());
        }
        System.out.println(maxVal);
    }
}
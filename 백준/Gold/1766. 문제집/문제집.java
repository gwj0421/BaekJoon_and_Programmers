import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> relationship = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            relationship.put(i, new ArrayList<>());
        }
        int[] inCnt = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int aft = Integer.parseInt(st.nextToken());
            inCnt[aft]++;
            relationship.get(pre).add(aft);
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i < n + 1; i++) {
            if (inCnt[i] == 0) {
                queue.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");
            for (int next : relationship.get(now)) {
                if (--inCnt[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        System.out.println(sb);

    }
}

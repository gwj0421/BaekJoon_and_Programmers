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

        Map<Integer, List<Integer>> rule = new HashMap<>();
        int[] inDegree = new int[n + 1];
        for (int i = 1; i < n+1; i++) {
            rule.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            rule.get(a).add(b);
            inDegree[b]++;
        }
        Queue<Integer> remain = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0) {
                remain.offer(i);
            }
        }

        while (!remain.isEmpty()) {
            int now = remain.poll();
            sb.append(now).append(" ");
            for (int next : rule.get(now)) {
                if (--inDegree[next] == 0) {
                    remain.offer(next);
                }
            }
        }
        System.out.println(sb);

    }
}

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
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] requireTime = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                requireTime[j] = Integer.parseInt(st.nextToken());
            }
            int[] inDegree = new int[n + 1];
            Map<Integer, List<Integer>> rule = new HashMap<>();
            for (int j = 1; j < n + 1; j++) {
                rule.put(j, new ArrayList<>());
            }
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                inDegree[end]++;
                rule.get(start).add(end);
            }
            int finishIdx = Integer.parseInt(br.readLine());

            Queue<Integer> remain = new LinkedList<>();
            int[] result = new int[n + 1];
            for (int j = 1; j < n + 1; j++) {
                result[j] = requireTime[j];
                if (inDegree[j] == 0) {
                    remain.add(j);
                }
            }
            while (!remain.isEmpty()) {
                int now = remain.poll();
                for (int next : rule.get(now)) {
                    result[next] = Math.max(result[next], result[now] + requireTime[next]);
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        remain.add(next);
                    }
                }
            }
            System.out.println(result[finishIdx]);
        }
    }
}

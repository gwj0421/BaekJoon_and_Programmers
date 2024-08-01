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

        int[] inDegree = new int[n + 1];
        Map<Integer, List<Integer>> rule = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            rule.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] eachLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < eachLine[0]; j++) {
                rule.get(eachLine[j]).add(eachLine[j + 1]);
                inDegree[eachLine[j + 1]]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append("\n");

            for (int next : rule.get(now)) {
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] > 0) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(sb);
    }

}

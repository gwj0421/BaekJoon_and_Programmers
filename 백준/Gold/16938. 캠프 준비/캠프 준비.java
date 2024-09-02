import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 0 1 10 11 100 101 110 111
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] problems = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(problems);
        int ans = 0;
        for (int i = 1; i < (int) Math.pow(2, n); i++) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = 0; j <= n; j++) {
                if ((i & (1 << j)) != 0) {
                    deque.push(problems[n - 1 - j]);
                }
            }
            if (deque.size() > 1) {
                if (deque.peekLast() - deque.peekFirst() < x) {
                    continue;
                }
                int sum = 0;
                while (!deque.isEmpty()) {
                    sum += deque.poll();
                }
                if (sum < l || sum > r) {
                    continue;
                }
                ans++;
            }
        }
        System.out.println(ans);
    }
}
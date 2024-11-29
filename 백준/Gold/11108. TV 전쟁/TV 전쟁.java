import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 3 ~ 11 = 10
    // 1 ~ 5 = 6
    // 6 ~ 10 = 5
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<Program> schedule = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                schedule.add(new Program(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            Tv tv = new Tv(schedule);
            System.out.println(tv.getMaxPreference());
        }
    }

    static class Tv {
        private static final int MAX_TIME = 10080;
        private int[] dp;
        private PriorityQueue<Program> schedule;

        public Tv(PriorityQueue<Program> schedule) {
            this.schedule = schedule;
            this.dp = new int[MAX_TIME + 1];
        }

        // 0 1 2 3 4 5 6 7 8 9 10 11 12
        // 0 0 0 0 0 6 6 6 6 6 11
        public int getMaxPreference() {
            for (int i = 1; i <= MAX_TIME; i++) {
                dp[i] = dp[i - 1];
                while (!schedule.isEmpty() && schedule.peek().endTime == i) {
                    Program program = schedule.poll();
                    dp[i] = Math.max(dp[i], dp[program.s] + program.p);
                }
            }
            return dp[MAX_TIME];
        }
    }

    static class Program implements Comparable<Program> {
        private int s;
        private int d;
        private int p;
        private int endTime;

        public Program(int s, int d, int p) {
            this.s = s;
            this.d = d;
            this.p = p;
            this.endTime = s + d;
        }

        @Override
        public int compareTo(Program o) {
            return Integer.compare(this.endTime, o.endTime);
        }

    }
}



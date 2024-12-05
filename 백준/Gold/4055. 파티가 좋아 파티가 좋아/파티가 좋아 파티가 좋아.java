import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 9 10, 9 10, 9 11, 12 13 , 12 13, 12 14,
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int testIdx = 0;
        while (true) {
            testIdx++;
            int p = Integer.parseInt(br.readLine());
            if (p == 0) {
                return;
            }
            List<Party> totalParties = new ArrayList<>();
            StringTokenizer st;
            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine());
                totalParties.add(new Party(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
            }
            Collections.sort(totalParties);
//            System.out.println(totalParties);

            int[] schedule = new int[25];
            int cnt = 0;
            for (int time = 8; time <= 23; time++) {
                for (int d = 0; d < 2; d++) {
                    for (int i = 0; i < totalParties.size(); i++) {
                        Party cur = totalParties.get(i);
                        if (cur.st <= time && time <= cur.en) {
                            cnt++;
                            totalParties.remove(i);
                            break;
                        }
                    }
                }
            }
            System.out.println(String.format("On day %d Emma can attend as many as %d parties.", testIdx, cnt));
        }
    }

    static class Party implements Comparable<Party> {
        private final int st;
        private final int en;

        public Party(int st, int en) {
            this.st = st;
            this.en = en;
        }

        @Override
        public int compareTo(Party o) {
            if (this.en == o.en) {
                return Integer.compare(o.st, this.st);
            }
            return Integer.compare(this.en, o.en);
        }

        @Override
        public String toString() {
            return "Party{" +
                    "st=" + st +
                    ", en=" + en +
                    '}';
        }
    }

    static class Time {
        private final int min;
        private final int sec;

        public Time(int min, int sec) {
            this.min = min;
            this.sec = sec;
        }
    }
}



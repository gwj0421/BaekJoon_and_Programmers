import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        Scanner sc = new Scanner(System.in);

        Algorithm algorithm = new Algorithm();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(algorithm.solve(n));
        }
    }

    static class Algorithm {
        private final List<Integer> numbers;

        // 1 2 3 4 5 6 7 8 9
        // 12 13 14 15 16 17 18 19
        // 21 23 24 25 26 27 28 29
        public Algorithm() {
            this.numbers = new ArrayList<>();
            initNumbers(0, 0, new boolean[10]);
            Collections.sort(this.numbers);
        }

        public int solve(int n) {
            int l = 0, r = numbers.size() - 1, ans = -1;
            int m;
            while (l <= r) {
                m = (l + r) / 2;
                if (n < numbers.get(m)) {
                    ans = numbers.get(m);
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            if (ans < 0) {
                return 0;
            }

            return ans;
        }

        private void initNumbers(int depth, int num, boolean[] used) {
            if (depth > 9) {
                return;
            }
            for (int i = 1; i < 10; i++) {
                if (!used[i]) {
                    used[i] = true;
                    numbers.add(num * 10 + i);
                    initNumbers(depth + 1, num * 10 + i, used);
                    used[i] = false;
                }
            }
        }
    }
}



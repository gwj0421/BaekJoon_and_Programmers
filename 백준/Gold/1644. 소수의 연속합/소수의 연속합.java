import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Algorithm algorithm = new Algorithm(n);
        algorithm.solve();
    }

    static class Algorithm {
        private final int n;
        private final boolean[] eratosthenes;
        private List<Integer> primeSum;

        // 2 3 5 7 11 13 17 19 23 29 31 37 41
        // 2 5 10 17 28 31 48 67 90 10
        public Algorithm(int n) {
            this.n = n;
            this.eratosthenes = initEratosthenes();
            List<Integer> primeSum = new ArrayList<>();
            primeSum.add(0);
            for (int i = 1; i < this.eratosthenes.length; i++) {
                if (!this.eratosthenes[i]) {
                    primeSum.add(primeSum.get(primeSum.size() - 1) + i);
                }
            }
            this.primeSum = primeSum;
        }

        public void solve() {
            int left = 0, right = 0;
            int value;
            int cnt = 0;
            while (left < primeSum.size() && right < primeSum.size()) {
                value = primeSum.get(right) - primeSum.get(left);
                if (value < n) {
                    right++;
                } else if (value > n) {
                    left++;
                } else {
                    cnt++;
                    left++;
                    right++;
                }
            }
            System.out.println(cnt);
        }

        private boolean[] initEratosthenes() {
            boolean[] eratosthenes = new boolean[n + 1];
            eratosthenes[0] = true;
            eratosthenes[1] = true;
            for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
                for (int j = i * i; j <= n; j += i) {
                    eratosthenes[j] = true;
                }
            }
            return eratosthenes;
        }
    }
}



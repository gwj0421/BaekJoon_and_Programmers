import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ConvexNumbers convexNumbers = new ConvexNumbers(n, numbers);
        convexNumbers.activate();

    }

    static class ConvexNumbers {
        private final int n;
        private int[] numbers;
        private boolean isEnd;
        private long cnt;

        public ConvexNumbers(int n, int[] numbers) {
            this.n = n;
            this.numbers = numbers;
            this.isEnd = false;
            this.cnt = 0;
        }

        public void activate() {
            while (!isEnd) {
                isEnd = true;
                for (int i = 1; i < n - 1; i++) {
                    if (!isConvex(i)) {
                        int dif = numbers[i] - (numbers[i - 1] + numbers[i + 1]) / 2;
                        cnt += dif;
                        numbers[i] -= dif;
                        isEnd = false;
                    }
                }
            }
            System.out.println(cnt);
        }

        private boolean isConvex(int idx) {
            return numbers[idx - 1] + numbers[idx + 1] >= 2 * numbers[idx];
        }
    }
}
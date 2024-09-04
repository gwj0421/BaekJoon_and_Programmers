import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int origin = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n - 2];
        for (int i = 0; i < n - 2; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int result = Integer.parseInt(st.nextToken());
        Calculator calculator = new Calculator(origin, numbers, result);
        calculator.search();
    }


    static class Calculator {
        private final int origin;
        private final int[] numbers;
        private final int result;
        private final int numberLength;
        private long cnt;

        public Calculator(int origin, int[] numbers, int result) {
            this.origin = origin;
            this.numbers = numbers;
            this.result = result;
            this.numberLength = numbers.length;
            this.cnt = 0;
        }

        public void search() {
            long[] cnt = new long[21];
            cnt[origin] = 1;
            int tmpNum;
            for (int i = 0; i < numbers.length; i++) {
                long[] tmp = new long[21];
                for (int j = 0; j < 21; j++) {
                    tmpNum = j + numbers[i];
                    if (0 <= tmpNum && tmpNum <= 20) {
                        tmp[tmpNum] += cnt[j];
                    }
                    tmpNum = j - numbers[i];
                    if (0 <= tmpNum && tmpNum <= 20) {
                        tmp[tmpNum] += cnt[j];
                    }
                }
                cnt = tmp;
            }
            System.out.println(cnt[result]);
        }

    }
}
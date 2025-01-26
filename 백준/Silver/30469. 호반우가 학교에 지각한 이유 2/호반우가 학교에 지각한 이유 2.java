import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        Algorithm algorithm = new Algorithm(a, b, n);
        algorithm.printResult();
    }

}

class Algorithm {
    private static final int MAX_NUM = 100;
    private final int a;
    private final int b;
    private final int n;
    private final boolean[] prime;
    private int[] numbers;

    public Algorithm(int a, int b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.prime = initPrime();
        this.numbers = initNumbers(a, b);
    }


    private boolean[] initPrime() {
        boolean[] prime = new boolean[MAX_NUM + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; (i * i) <= MAX_NUM; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= MAX_NUM; j += i) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

    private int[] initNumbers(int a, int b) {
        int[] numbers = new int[n];
        numbers[0] = a / 10;
        numbers[1] = a % 10;
        numbers[n - 2] = b / 10;
        numbers[n - 1] = b % 10;
        return numbers;
    }

    public void printResult() {
        if (makeSosusosu()) {
            StringBuilder sb = new StringBuilder();
            for (int number : numbers) {
                sb.append(number);
            }
            System.out.println(sb);
            return;
        }
        System.out.println(-1);
    }

    private boolean makeSosusosu() {
        for (int i = 2; i < n - 2; i++) {
            if (!searchPrime(i - 1, i)) {
                return false;
            }
        }

        return checkPrime(numbers[n - 3], numbers[n - 2]);
    }

    private boolean searchPrime(int beforeIdx, int nowIdx) {
        for (int i = 1; i < 10; i++) {
            if (checkPrime(numbers[beforeIdx], i)) {
                numbers[nowIdx] = i;
                return true;
            }
        }
        return false;
    }

    private boolean checkPrime(int num1, int num2) {
        return prime[num1 * 10 + num2];
    }

}
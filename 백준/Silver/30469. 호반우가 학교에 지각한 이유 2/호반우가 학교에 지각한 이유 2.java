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

        boolean[] prime = initPrime();
        int[] sosu = new int[n];
        sosu[0] = a / 10;
        sosu[1] = a % 10;
        sosu[n - 2] = b / 10;
        sosu[n - 1] = b % 10;
        for (int i = 2; i < n - 2; i++) {
            boolean flag = false;
            for (int j = 1; j < 10; j++) {
                if (checkPrime(sosu[i - 1], j, prime)) {
                    sosu[i] = j;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(-1);
                return;
            }
        }
        if (!checkPrime(sosu[n - 3], sosu[n - 2], prime)) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : sosu) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }

    public static boolean[] initPrime() {
        boolean[] prime = new boolean[101];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; (i * i) < 101; i++) {
            if (prime[i]) {
                for (int j = i * i; j < 101; j += i) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

    public static boolean checkPrime(int num1, int num2, boolean[] prime) {
        return prime[num1 * 10 + num2];
    }
}

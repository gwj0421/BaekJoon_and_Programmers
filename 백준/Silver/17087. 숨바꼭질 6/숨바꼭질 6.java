import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] brothers = Arrays.stream(br.readLine().split(" ")).mapToInt(it -> Math.abs(s - Integer.parseInt(it))).toArray();
        int last = brothers[0];
        for (int i = 1; i < n; i++) {
            last = gcd(last, brothers[i]);
        }
        System.out.println(last);
    }

    private static int gcd(int a, int b) {
        int remain = a % b;
        while (remain != 0) {
            a = b;
            b = remain;
            remain = a % b;
        }
        return b;
    }
}
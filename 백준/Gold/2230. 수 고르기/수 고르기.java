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
        int m = Integer.parseInt(st.nextToken());

        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(sequence);
//        System.out.println(Arrays.toString(sequence));

        int l = 0, r = 0, ans = Integer.MAX_VALUE;
        while (l < n && r < n) {
            if (sequence[r] - sequence[l] >= m) {
                ans = Math.min(ans, sequence[r] - sequence[l]);
                l++;
            } else {
                r++;
            }
        }
        System.out.println(ans);
    }
}
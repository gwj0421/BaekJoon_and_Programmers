import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    // 1 2 3 4 5 6 7 8
    // 1 2 2 2 3 3 3 7 - 13
    // 1 2 3 2 2 3 3 7 -
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long ans = 0;
        long[] grades = new long[n];
        for (int i = 0; i < n; i++) {
            grades[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(grades);

        for (int i = 1; i < n + 1; i++) {
            ans += Math.abs(grades[i - 1] - (long) i);
        }
        System.out.println(ans);
    }
}


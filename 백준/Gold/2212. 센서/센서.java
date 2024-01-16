import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] road = new int[n];
        String[] inputLine = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            road[i] = Integer.parseInt(inputLine[i]);
        }
        Arrays.sort(road);
//        System.out.println(Arrays.toString(road));
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = road[i + 1] - road[i];
        }
        Arrays.sort(diff);
//        System.out.println(Arrays.toString(diff));
        int ans = 0;
        for (int i = 0; i < n - k; i++) {
            ans += diff[i];
        }
        System.out.println(ans);
    }
}
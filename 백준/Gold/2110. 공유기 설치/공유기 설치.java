import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 1 2 4 8 9
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int start = 1;
        int end = houses[n - 1] - houses[0];
        int maxDist = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int dist = 0;
            int cnt = 1;
            for (int i = 1; i < n; i++) {
                if (dist + houses[i] - houses[i - 1] >= mid) {
                    cnt++;
                    dist = 0;
                } else {
                    dist += houses[i] - houses[i - 1];
                }
            }
            if (cnt >= c) {
                maxDist = Math.max(maxDist, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(maxDist);
    }
}
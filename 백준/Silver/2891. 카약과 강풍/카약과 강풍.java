import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] boat = new int[n + 1];
        Arrays.fill(boat, 1);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            boat[Integer.parseInt(st.nextToken())]--;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            boat[Integer.parseInt(st.nextToken())]++;
        }

        for (int i = 1; i < n + 1; i++) {
            if (boat[i] > 1) {
                if (boat[i - 1] == 0) {
                    boat[i - 1]++;
                    boat[i]--;
                } else if (i < n && boat[i + 1] == 0) {
                    boat[i + 1]++;
                    boat[i]--;
                }
            }
        }
        int failTeamCnt = 0;
        for (int boatCnt : boat) {
            if (boatCnt == 0) {
                failTeamCnt++;
            }
        }
        System.out.println(failTeamCnt);
    }
}
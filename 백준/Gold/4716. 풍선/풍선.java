import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (n == 0 && a == 0 && b == 0) {
                break;
            }
            int[][] info = new int[n][];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int da = Integer.parseInt(st.nextToken());
                int db = Integer.parseInt(st.nextToken());
                info[i] = new int[]{k, da, db, Math.abs(da - db)};
            }
            Arrays.sort(info, (o1, o2) -> o2[3] - o1[3]);
            int minDist = 0;
            for (int i = 0; i < n; i++) {
                int[] now = info[i];
                if (now[1] < now[2]) {
                    if (now[0] <= a) {
                        a -= now[0];
                        minDist += now[0] * now[1];
                    } else {
                        minDist += a * now[1];
                        now[0] -= a;
                        a = 0;
                        b -= now[0];
                        minDist += now[0] * now[2];
                    }
                } else {
                    if (now[0] <= b) {
                        b -= now[0];
                        minDist += now[0] * now[2];
                    } else {
                        minDist += b * now[2];
                        now[0] -= b;
                        b = 0;
                        a -= now[0];
                        minDist += now[0] * now[1];
                    }
                }
            }
            System.out.println(minDist);
        }
    }
}
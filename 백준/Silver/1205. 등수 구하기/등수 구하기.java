import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        if (n == 0) {
            System.out.println(1);
            return;
        }
        int score = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        List<Integer> scoreboard = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            scoreboard.add(Integer.parseInt(st.nextToken()));
        }
        scoreboard.sort(Comparator.reverseOrder());

        if (n == p && score <= scoreboard.get(n - 1)) {
            System.out.println(-1);
        } else {
            int rank = 1;
            for (int i = 0; i < n; i++) {
                if (score < scoreboard.get(i)) {
                    rank++;
                } else {
                    break;
                }
            }
            System.out.println(rank);
        }
    }
}
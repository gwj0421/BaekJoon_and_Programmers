import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<int[]> countries = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            String[] temp = br.readLine().split(" ");
            int[] nation = new int[4];
            for (int j = 0; j < 4; j++) {
                nation[j] = Integer.parseInt(temp[j]);
            }
            countries.add(nation);
        }
        countries.sort((o1, o2) -> {
            if (o1[1] > o2[1] || (o1[1] == o2[1] && o1[2] > o2[2]) || (o1[1] == o2[1] && o1[2] == o2[2] && o1[3] > o2[3])) {
                return -1;
            }
            return 1;
        });

        int rank = 1;
        for (int i = 1; i < n; i++) {
            if (!isSameRecord(countries.get(i - 1), countries.get(i))) {
                rank++;
            }
            if (countries.get(i)[0] == k) {
                System.out.println(rank);
                return;
            }
        }
    }

    private static boolean isSameRecord(int[] a, int[] b) {
        return a[1] == b[1] && a[2] == b[2] && a[3] == b[3];
    }
}
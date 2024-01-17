import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] people = new int[n][];
        long stand = 0;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int xi = Integer.parseInt(st.nextToken());
            int ai = Integer.parseInt(st.nextToken());
            stand += ai;
            people[i] = new int[]{xi, ai};
        }

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        long pCnt = 0;
        stand = (stand + 1) / 2;
        for (int[] person : people) {
            pCnt += person[1];
            if (pCnt >= stand) {
                System.out.println(person[0]);
                break;
            }
        }


    }
}
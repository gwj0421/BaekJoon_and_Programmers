import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static int N;
    private static List<Integer> Ai = new ArrayList<>();
    private static int B;
    private static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (String ai : br.readLine().split(" ")) {
            Ai.add(Integer.parseInt(ai));
        }
        String[] bc = br.readLine().split(" ");
        B = Integer.parseInt(bc[0]);
        C = Integer.parseInt(bc[1]);

        long spectorCnt = 0;
        for (Integer ai : Ai) {
            int requireCnt = 1;
            // Ai <= B + Cx
            if (ai > B) {
                ai -= B;
                if (ai % C != 0) {
                    requireCnt += 1;
                }
                requireCnt +=  ai / C;
            }
            spectorCnt += requireCnt;
        }
        System.out.println(spectorCnt);
    }
}


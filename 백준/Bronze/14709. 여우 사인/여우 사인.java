import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 1 3 6 10 15 21

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        boolean[][] foxSign = new boolean[6][6];
        foxSign[1][3] = foxSign[3][1] = true;
        foxSign[1][4] = foxSign[4][1] = true;
        foxSign[3][4] = foxSign[4][3] = true;
        boolean[][] isTouched = new boolean[6][6];

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            isTouched[p1][p2] = true;
            isTouched[p2][p1] = true;
            if (!foxSign[p1][p2]) {
                System.out.println("Woof-meow-tweet-squeek");
                return;
            }
        }

        if (!isTouched[1][3] || !isTouched[1][4] || !isTouched[3][4]) {
            System.out.println("Woof-meow-tweet-squeek");
            return;
        }
        System.out.println("Wa-pa-pa-pa-pa-pa-pow!");
    }
}



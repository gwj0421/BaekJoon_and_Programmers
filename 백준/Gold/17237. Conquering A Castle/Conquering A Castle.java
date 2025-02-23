import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());

        int[] army = new int[21];
        for (int i = 0; i < n; i++) {
            int armyDist = Integer.parseInt(br.readLine());
            army[armyDist]++;
        }
        for (int i = 20; i > 1; i--) {
            army[i - 1] += army[i] / 2;
        }
        if (army[1] > 1) {
            System.out.println("A");
        } else {
            System.out.println("B");
        }
    }
}
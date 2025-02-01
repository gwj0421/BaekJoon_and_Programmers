import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final boolean DEBUG_MODE = false;

    // 1-2 , 1-4 , 4-3
    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        double[] dart = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double bob = 10.5;
        for (int i = 0; i < dart.length; i++) {
            if (dart[i] == 20) {
                double alice = (dart[i > 0 ? i - 1 : dart.length - 1] + dart[i] + dart[(i + 1) % dart.length]) / 3;
                if (alice > bob) {
                    System.out.println("Alice");
                } else if (alice == bob) {
                    System.out.println("Tie");
                } else {
                    System.out.println("Bob");
                }
                break;
            }
        }
    }
}
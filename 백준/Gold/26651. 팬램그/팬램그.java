import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int x = Integer.parseInt(br.readLine());
        if (x == 0) {
            System.out.println("GBSISTHEBEST");
            return;
        }
        // a*b+c+52 <= 100_000
        int a = (int) Math.sqrt(x);
        int b = a;
        while (a * b <= x) {
            b++;
        }
        b--;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
            sb.append("A");
        }
        sb.append("BCDEFGHIJKLMNOPQRSTUVWXY");
        for (int i = 0; i < b; i++) {
            sb.append("Z");
        }
        for (int i = 0; i < x - a * b; i++) {
            sb.append("A");
        }
        sb.append("BCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println(sb);
    }

}
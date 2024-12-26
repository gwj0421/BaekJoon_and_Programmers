import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        if (n == 0) {
            sb.append(0);
        } else {
            while (n != 1) {
                sb.append(Math.abs(n % -2));
                n = (int) Math.ceil((double) n / -2);
            }
            sb.append(n);
        }
        System.out.println(sb.reverse());
    }
}



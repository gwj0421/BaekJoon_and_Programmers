import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        for (int t = Integer.parseInt(br.readLine()); t > 0; t--) {
            String word = br.readLine();
            if (word.length() == 1) {
                System.out.println(word + "\n" + word);
                continue;
            }
            String odd = "";
            String even = "";
            for (int i = 0; i < word.length(); i++) {
                if (i % 2 == 1) {
                    odd += word.charAt(i);
                } else {
                    even += word.charAt(i);
                }
            }

            if (word.length() % 2 == 0) {
                System.out.println(even + "\n" + odd);
            } else {
                System.out.println(even + odd + "\n" + odd + even);
            }
        }
    }
}



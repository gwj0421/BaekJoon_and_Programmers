import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String paper = br.readLine();
        String word = br.readLine();
        int idx = 0;
        int cnt = 0;
        while (idx <= paper.length() - word.length()) {
            if (paper.substring(idx, idx + word.length()).equals(word)) {
                idx += word.length();
                cnt += 1;
            } else {
                idx += 1;
            }
        }
        System.out.println(cnt);

    }
}


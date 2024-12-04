import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        char[] word = br.readLine().toCharArray();
        int cnt = 0;
        for (int i = 0; i < word.length-3; i++) {
            if (word[i] == 'D' && word[i + 1] == 'K' && word[i + 2] == 'S' && word[i + 3] == 'H') {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}



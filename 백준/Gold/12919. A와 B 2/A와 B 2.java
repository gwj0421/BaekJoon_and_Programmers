import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String S;
    private static String T;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        dfs(T);
        System.out.println(0);
    }

    private static void dfs(String word) {
        if (word.equals(S)) {
            System.out.println(1);
            System.exit(0);
        }
        if (word.length() > S.length()) {
            if (word.charAt(word.length() - 1) == 'A') {
                dfs(word.substring(0, word.length() - 1));
            }
            if (word.charAt(0) == 'B') {
                StringBuilder sb = new StringBuilder(word.substring(1));
                dfs(sb.reverse().toString());
            }
        }
    }

}
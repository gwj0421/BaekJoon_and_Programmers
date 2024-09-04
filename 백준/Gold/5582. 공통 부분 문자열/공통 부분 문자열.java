import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        char[] word1 = br.readLine().toCharArray();
        char[] word2 = br.readLine().toCharArray();
        // _ A B R A C A D A B R A
        // E 0 0 0 0 0 0 0 0 0 0 0
        // C 0 0 0 0 1 0 0 0 0 0 0
        // A 1 0 0 1 0 2 0 1 0 0 1
        // D 0 0 0 0 0 0 3 0 0 0 0

        int word1Length = word1.length;
        int word2Length = word2.length;

        int[][] dp = new int[word1Length + 1][word2Length + 1];
        int maxVal = 0;

        for (int i = 1; i < word1Length+1; i++) {
            for (int j = 1; j < word2Length+1; j++) {
                if (word1[i-1] == word2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxVal = Math.max(maxVal, dp[i][j]);
                }
            }
        }

        System.out.println(maxVal);
    }
}
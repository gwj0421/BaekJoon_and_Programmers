import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static Map<Integer, Map<Character, Character>> CONVERT_RULE
            = Map.of(
            1, Map.of(
                    'd', 'q'
                    , 'q', 'd'
                    , 'b', 'p'
                    , 'p', 'b'
            ),
            2, Map.of(
                    'd', 'b'
                    , 'b', 'd'
                    , 'q', 'p'
                    , 'p', 'q'
            )
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        char[][] alphabetBoard = new char[n][n];
        char[] inputLine;
        for (int i = 0; i < n; i++) {
            inputLine = br.readLine().toCharArray();
            for (int j = 0; j < inputLine.length; j++) {
                alphabetBoard[i][j] = CONVERT_RULE.get(d).get(inputLine[j]);
            }
        }
        for (char[] board : alphabetBoard) {
            System.out.println(String.copyValueOf(board));
        }

    }
}



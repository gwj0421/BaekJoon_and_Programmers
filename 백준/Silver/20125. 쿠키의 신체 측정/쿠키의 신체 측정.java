import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static final Map<Character, Integer> NUMBER_OF_PLAYING = Map.of('Y', 1, 'F', 2, 'O', 3);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][];
        boolean isDetect = false;
        int[] heart = new int[2];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (!isDetect && board[i][j] == '*') {
                    heart = new int[]{i + 1, j};
                    isDetect = true;
                }
            }
        }

        int lh = 0;
        int rh = 0;
        int ll = 0;
        int rl = 0;
        int w = 0;

        for (int i = heart[1] + 1; i < n; i++) {
            if (board[heart[0]][i] == '*') {
                rh++;
            } else {
                break;
            }
        }

        for (int i = heart[1] - 1; i > -1; i--) {
            if (board[heart[0]][i] == '*') {
                lh++;
            } else {
                break;
            }
        }
        int[] leftLeg = new int[2];
        int[] rigihtLeg = new int[2];
        for (int i = heart[0] + 1; i < n; i++) {
            if (board[i][heart[1]] == '*') {
                w++;
            } else {
                leftLeg = new int[]{i, heart[1] - 1};
                rigihtLeg = new int[]{i, heart[1] + 1};
                break;
            }
        }

        for (int i = leftLeg[0]; i < n; i++) {
            if (board[i][leftLeg[1]] == '*') {
                ll++;
            } else {
                break;
            }
        }
        for (int i = rigihtLeg[0]; i < n; i++) {
            if (board[i][rigihtLeg[1]] == '*') {
                rl++;
            } else {
                break;
            }
        }
        System.out.println((heart[0] + 1) + " " + (heart[1] + 1));
        System.out.println(lh + " " + rh + " " + w + " " + ll + " " + rl);
    }
}
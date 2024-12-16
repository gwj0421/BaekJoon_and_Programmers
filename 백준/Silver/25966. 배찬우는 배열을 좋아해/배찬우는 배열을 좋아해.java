import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            order[i] = i;
        }

        int queryType, i, j, k;
        for (int queryIdx = 0; queryIdx < q; queryIdx++) {
            st = new StringTokenizer(br.readLine());
            queryType = Integer.parseInt(st.nextToken());
            if (queryType == 0) {
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());
                board[order[i]][j] = k;
            } else if (queryType == 1) {
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());
                k = order[i];
                order[i] = order[j];
                order[j] = k;
            } else {
                throw new IllegalArgumentException();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int l = 0; l < n; l++) {
            for (int value : board[order[l]]) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}



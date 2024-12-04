import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][m];
        Coord p1 = null;
        Coord p2 = null;

        for (int i = 0; i < n; i++) {
            char[] inputLine = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (inputLine[j] == '#') {
                    if (p1 == null) {
                        p1 = new Coord(i, j);
                    } else {
                        p2 = new Coord(i, j);
                    }
                }
            }
            board[i] = inputLine;
        }
        if (board[p1.y][(p1.x + p2.x) / 2] == '.') {
            System.out.println("UP");
        } else if (board[(p1.y + p2.y) / 2][p1.x] == '.') {
            System.out.println("LEFT");
        } else if (board[(p1.y + p2.y) / 2][p2.x] == '.') {
            System.out.println("RIGHT");
        } else {
            System.out.println("DOWN");
        }
    }

    static class Coord {
        private final int y;
        private final int x;

        public Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}



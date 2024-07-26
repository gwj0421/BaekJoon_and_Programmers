import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] sudoku = new int[9][];
        for (int i = 0; i < 9; i++) {
            sudoku[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        Game game = new Game(sudoku);
        game.solve();
    }

    static class Game {
        private int[][] sudoku;

        public Game(int[][] sudoku) {
            this.sudoku = sudoku;
        }

        public int[][] getSudoku() {
            return sudoku;
        }

        public void solve() {
            dfs(0);

        }

        public void dfs(int order) {
            if (order == 81) {
                StringBuilder sb = new StringBuilder();
                for (int[] numbers : sudoku) {
                    for (int number : numbers) {
                        sb.append(number);
                    }
                    sb.append("\n");
                }
                System.out.println(sb);
                System.exit(0);
            }
            int y = order / 9;
            int x = order % 9;
            if (getSudoku()[y][x] != 0) {
                dfs(order + 1);
                return;
            }
            Set<Integer> expectNumbers = checkWidthAndLength(y, x);
            if (expectNumbers.isEmpty()) {
                return;
            }
            for (int expectNum : expectNumbers) {
                getSudoku()[y][x] = expectNum;
                dfs(order + 1);
                getSudoku()[y][x] = 0;
            }

        }

        public Set<Integer> checkWidthAndLength(int y, int x) {
            Set<Integer> able = new HashSet<>(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for (int i = 0; i < 9; i++) {
                able.remove(getSudoku()[y][i]);
                able.remove(getSudoku()[i][x]);
            }
            int wy = 0;
            while (y > 2) {
                y -= 3;
                wy++;
            }
            int wx = 0;
            while (x > 2) {
                x -= 3;
                wx++;
            }
            for (int i = wy * 3; i < wy * 3 + 3; i++) {
                for (int j = wx * 3; j < wx * 3 + 3; j++) {
                    able.remove(getSudoku()[i][j]);
                }
            }
            return able;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[][] ingredients = new int[n][];
        for (int i = 0; i < n; i++) {
            ingredients[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Cooking cooking = new Cooking(n, ingredients);
        cooking.search();
    }

    static class Cooking {
        private final int n;
        private final int[][] ingredients;
        private int minVal;

        public Cooking(int n, int[][] ingredients) {
            this.n = n;
            this.ingredients = ingredients;
            this.minVal = 1_000_000_000;
        }

        public void search() {
            dfs(0, 1,0);
            System.out.println(minVal);
        }

        public void dfs(int depth, int val1, int val2) {
            if (depth > n - 1) {
                if (val1 != 1 && val2 != 0) {
                    minVal = Math.min(minVal, Math.abs(val1 - val2));
                }
                return;
            }
            dfs(depth + 1, val1 * ingredients[depth][0], val2 + ingredients[depth][1]);
            dfs(depth + 1, val1, val2);
        }

    }
}




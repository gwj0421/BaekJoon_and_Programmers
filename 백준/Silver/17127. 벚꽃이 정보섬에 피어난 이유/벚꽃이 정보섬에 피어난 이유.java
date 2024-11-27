import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] flowers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        FlowerLand flowerLand = new FlowerLand(n, flowers);
        flowerLand.search();

    }

    static class FlowerLand {
        private final int n;
        private final int[] flowers;
        private int ans = 0;

        FlowerLand(int n, int[] flowers) {
            this.n = n;
            this.flowers = flowers;
            this.ans = 0;
        }

        public void search() {
            int tmp, score;
            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        tmp = 1;
                        score = 0;
                        for (int l = 0; l < n; l++) {
                            if (l == i || l == j || l == k) {
                                score += tmp;
                                tmp = flowers[l];
                            } else {
                                tmp *= flowers[l];
                            }
                        }
                        score += tmp;
                        ans = Math.max(ans, score);
                    }
                }
            }
            System.out.println(ans);
        }

    }

}



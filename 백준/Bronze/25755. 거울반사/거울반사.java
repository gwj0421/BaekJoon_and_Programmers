import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        char pattern = st.nextToken().charAt(0);
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Converter converter = new Converter(n, pattern, arr);
        converter.convert();
    }

    static class Converter {
        private static Map<Integer, String> PATTERN = Map.of(
                1, "1",
                2, "5",
                3, "?",
                4, "?",
                5, "2",
                6, "?",
                7, "?",
                8, "8",
                9, "?"
        );
        private final int n;
        private final char patternType;
        private final int[][] arr;
        private String[][] result;

        public Converter(int n, char patternType, int[][] arr) {
            this.n = n;
            this.patternType = patternType;
            this.arr = arr;
            this.result = new String[n][n];
        }

        public void convert() {
            if (patternType == 'L' || patternType == 'R') {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        result[i][n-1-j] = PATTERN.get(arr[i][j]);
                    }
                }
            } else if (patternType == 'U' || patternType == 'D') {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        result[n-1-i][j] = PATTERN.get(arr[i][j]);
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
            for (String[] strings : result) {
                System.out.println(Arrays.stream(strings).collect(Collectors.joining(" ")));
            }
        }
    }
}
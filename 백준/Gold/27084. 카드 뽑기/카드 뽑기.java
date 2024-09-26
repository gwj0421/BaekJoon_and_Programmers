import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    // 1 2 3 1 = x
    // 1 2 3 x = o
    // 1 2 x 1 = x
    // 1 2 x x = o

    // 1 x 3 1 = x
    // 1 x 3 x = o
    // 1 x x 1 = x
    // 1 x x x = o

    // x 2 3 1 = o
    // x 2 3 x = o
    // x 2 x 1 = o
    // x 2 x x = o

    // x x 3 1 = o
    // x x 3 x = o
    // x x x 1 = o xxx
    // x x x x = re = o

    // 11 / 15 * 15 = 11

    // 1 2 3 1
    // 12 13 23 21 31
    // 123 231

    // 15 -
    // 11 121 131 1231

    // 1 2 3 1 1
    // a 2 3 b c
    // ab a2b a3b abc a23b a2bc a3bc a23bc
    // bc abc 2bc 3bc a2bc 23bc a3bc a23bc
    // ac a2c a3c abc a23c a3bc a2bc a23bc

    // ab a2b a3b abc a23b a2bc a3bc a23bc
    // bc 2bc 3bc 23bc
    // ac a2c a3c a23c
    // ab bc ac
    private static final int MOD = 1_000_000_000 + 7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int number : numbers) {
            if (cntMap.containsKey(number)) {
                cntMap.put(number, cntMap.get(number) + 1);
            } else {
                cntMap.put(number, 1);
            }
        }
        long ans = 1;
        for (Integer value : cntMap.values()) {
            ans = (ans * (value + 1)) % MOD;
        }
        System.out.println((ans - 1) % MOD);

    }
}
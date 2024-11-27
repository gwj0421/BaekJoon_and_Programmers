import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    //1 4 5 5 6 10
    //1 4 =>1
    //1 5 =>2
    //1 5 =>4
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        List<Long> menus = Arrays.stream(br.readLine().split(" ")).map(Long::parseLong).sorted().collect(Collectors.toList());
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += menus.get(i) * pow(i);
            ans -= menus.get(i) * pow(n - 1 - i);
            ans %= MOD;
        }
        System.out.println(ans);
    }

    public static long pow(int superscript) {
        if (superscript == 0) {
            return 1;
        }
        long temp = pow(superscript / 2);
        temp = temp * temp % MOD;
        if (superscript % 2 == 1) {
            return temp * 2 % MOD;
        }
        return temp;
    }

}



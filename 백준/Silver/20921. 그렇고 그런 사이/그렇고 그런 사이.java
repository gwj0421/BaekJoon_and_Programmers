import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 5(4) 4(3) 3(2) 2(1) 1(0)
    // 5 4 1 2 3 = 7

    // 4 3 2 1 = 3+2+1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<String> coupleLeft = new ArrayList<>();
        List<String> coupleRight = new ArrayList<>();
        int remain = k;
        for (int i = n; i > 0; i--) {
            if (i != 1 && remain >= i - 1) {
                remain -= (i - 1);
                coupleLeft.add(Integer.toString(i));
            } else {
                coupleRight.add(Integer.toString(i));
            }
        }
        Collections.reverse(coupleRight);
        coupleLeft.addAll(coupleRight);
        System.out.println(String.join(" ", coupleLeft));
    }
}
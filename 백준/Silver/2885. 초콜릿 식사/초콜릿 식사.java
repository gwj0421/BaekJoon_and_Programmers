import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int temp = 1;
        while (temp < K) {
            temp *= 2;
        }
        System.out.printf("%d ", temp);
        int cnt = 0;
        while (K > 0) {
            if (temp <= K) {
                K -= temp;
            } else {
                temp /= 2;
                cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}


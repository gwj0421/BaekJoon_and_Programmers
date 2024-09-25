import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * 초성 [i], 중성 [j], 종성 [k] 일 때,  [ 0xAC00 + (28*21*i) + (28*j) + k ]
     */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine()) - 1;
        int i = n / (21 * 28);
        int j = n % (21 * 28) / 28;
        int k = n % (21 * 28) % 28;
        System.out.println((char) (0xAC00 + (28 * 21 * i) + (28 * j) + k));
    }
}
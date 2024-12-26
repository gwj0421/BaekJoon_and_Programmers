import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer info = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(info.nextToken());
        int n = Integer.parseInt(info.nextToken());
        if (m == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(convert(m, n));
    }

    public static String convert(int m, int n) {
        StringBuilder sb = new StringBuilder();
        while (m > 0) {
            sb.append(convert2Char(m % n));
            m /= n;
        }
        return sb.reverse().toString();
    }

    private static char convert2Char(int order) {
        if (order > 9) {
            return (char) (55 + order);
        }
        return (char) (48 + order);
    }

}



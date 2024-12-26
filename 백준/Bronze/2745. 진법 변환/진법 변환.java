import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer info = new StringTokenizer(br.readLine());
        char[] n = info.nextToken().toCharArray();
        int b = Integer.parseInt(info.nextToken());

        int num = 0;
        for (int i = 0; i < n.length; i++) {
            num += convert(n[n.length - 1 - i]) * Math.pow(b, i);
        }
        System.out.println(num);
    }

    public static int convert(char word) {
        return word >= 65 ? word - 55 : word - 48;
    }
}



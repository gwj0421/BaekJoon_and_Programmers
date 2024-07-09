import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int cnt = 1;
        while (a <= b) {
            if (a == b) {
                System.out.println(cnt);
                return;
            }

            if (b % 10 == 1) {
                b = b / 10;
            } else if (b % 2 == 0) {
                b /= 2;
            } else {
                System.out.println(-1);
                return;
            }
            cnt++;
        }
        System.out.println(-1);
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();
        int xl = sc.nextInt();
        int xxl = sc.nextInt();
        int xxxl = sc.nextInt();
        int t = sc.nextInt();
        int p = sc.nextInt();

        int tshirt = 0;
        for (int i : new int[]{s, m, l, xl, xxl, xxxl}) {
            tshirt += calTShirt(i, t);
        }
        System.out.println(tshirt);
        System.out.println(n / p + " " + n % p);

    }

    public static int calTShirt(int cnt, int unit) {
        if (cnt % unit == 0) {
            return cnt / unit;
        }
        return cnt / unit + 1;
    }
}

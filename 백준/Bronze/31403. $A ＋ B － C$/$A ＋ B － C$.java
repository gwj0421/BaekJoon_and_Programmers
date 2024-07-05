import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        String c = sc.next();
        System.out.println(Integer.parseInt(a) + Integer.parseInt(b) - Integer.parseInt(c));
        System.out.println(Integer.parseInt(a + b) - Integer.parseInt(c));
    }
}

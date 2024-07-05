import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        int idx = sc.nextInt()-1;
        System.out.println(word.charAt(idx));
    }
}

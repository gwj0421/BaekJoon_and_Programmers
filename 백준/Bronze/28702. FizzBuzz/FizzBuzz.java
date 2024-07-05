import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        String[] question = new String[]{sc.next(), sc.next(), sc.next()};

        int i,number;
        for (i = 0; i < 3; i++) {
            try {
                number = Integer.parseInt(question[i]);
            } catch (NumberFormatException e) {
                continue;
            }

            int answer = number + 3 - i;
            System.out.println(transformFizzBuzz(answer));
            return;
        }
    }

    public static String transformFizzBuzz(int num) {
        if (num % 3 == 0 && num % 5 == 0) {
            return "FizzBuzz";
        }
        if (num % 3 == 0 && num % 5 != 0) {
            return "Fizz";
        }
        if (num % 3 != 0 && num % 5 == 0) {
            return "Buzz";
        }
        return Integer.toString(num);
    }
}

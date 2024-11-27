import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // T | T => T => T & F 2
    // T | F => T => T & F 1
    // F | F => F => F | T 1
    // T & T => T => T & F 1
    // T & F => F => T | F 1
    // F & F => F => F | T 2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        String[] formula = br.readLine().split(" ");
        String target = br.readLine();

        if (n == 1) {
            if (formula[0].equals(target)) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
            return;
        }

        String left = formula[0];
        for (int i = 1; i < n - 3; i += 2) {
            if (isTrue(left, formula[i], formula[i + 1])) {
                left = "T";
            } else {
                left = "F";
            }
        }
        String operator = formula[n - 2];
        String right = formula[n - 1];
        if (isTrue(left, operator, right) == (target.equals("T"))) {
            System.out.println(0);
        } else {
            if ((left.equals("F") && operator.equals("&") && right.equals("F"))
                    || (left.equals("T") && operator.equals("|") && right.equals("T"))) {
                System.out.println(2);
            } else {
                System.out.println(1);

            }
        }
    }

    private static boolean isTrue(String left, String operator, String right) {
        if (operator.equals("|")) {
            return left.equals("T") || right.equals("T");
        } else {
            return left.equals("T") && right.equals("T");
        }
    }
}



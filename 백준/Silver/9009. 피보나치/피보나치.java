import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    private static final int MAX_NUM = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int t = Integer.parseInt(br.readLine());

        List<Integer> fibonacci = new ArrayList<>(List.of(0, 1));

        int f1 = 0;
        int f2 = 1;
        int f;
        while (f1 + f2 <= MAX_NUM) {
            f = f1 + f2;
            fibonacci.add(f);
            f1 = f2;
            f2 = f;
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Stack<Integer> stack = new Stack<>();
            for (int j = fibonacci.size() - 1; j > -1; j--) {
                if (n - fibonacci.get(j) > -1) {
                    n -= fibonacci.get(j);
                    stack.add(fibonacci.get(j));
                    if (n == 0) {
                        break;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
            System.out.println(sb);
        }
    }

}



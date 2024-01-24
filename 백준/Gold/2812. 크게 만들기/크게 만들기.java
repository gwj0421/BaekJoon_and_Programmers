import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];
        String[] inputLine = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(inputLine[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int number : numbers) {
            while (!stack.isEmpty() && stack.peek() < number && k > 0) {
                k--;
                stack.pop();
            }
            stack.push(number);
        }
        while (k > 0) {
            k--;
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : stack) {
            sb.append(num);
        }
        System.out.println(sb.toString());
    }
}
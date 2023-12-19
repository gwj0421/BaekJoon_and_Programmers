import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cnt = new int[N + 1];
        int[] nearestBuilding = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
            nearestBuilding[i] = -100000;
        }

        // left
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < N + 1; i++) {
            while (!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
                stack.pop();
            }
            cnt[i] = stack.size();
            if (stack.size() > 0) {
                nearestBuilding[i] = stack.peek();
            }
            stack.push(i);
        }

        // right
        stack = new Stack<>();
        for (int i = N; i > 0; i--) {
            while (!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
                stack.pop();
            }
            cnt[i] += stack.size();
            if (stack.size() > 0 && Math.abs(i - stack.peek()) < Math.abs(i - nearestBuilding[i])) {
                nearestBuilding[i] = stack.peek();
            }
            stack.push(i);
        }

        for (int i = 1; i < N + 1; i++) {
            if (cnt[i] < 1) {
                System.out.println(0);
            } else {
                System.out.println(cnt[i] + " " + nearestBuilding[i]);
            }
        }
    }
}
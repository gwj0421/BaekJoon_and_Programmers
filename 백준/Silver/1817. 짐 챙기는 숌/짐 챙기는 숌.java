import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    private static final boolean DEBUG_MODE = false;
    // 12 / 1 11 / 2 10 / 3 4 5 / 6 6 / 1

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int[] books = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> boxes = new Stack<>();
        boxes.push(books[0]);
        for (int i = 1; i < n; i++) {
            if (boxes.peek() + books[i] <= m) {
                boxes.push(boxes.pop() + books[i]);
            } else {
                boxes.push(books[i]);
            }
        }
        System.out.println(boxes.size());
    }
}
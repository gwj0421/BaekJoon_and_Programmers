import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (a == 0 && b == 0) {
                break;
            }
            Stack<Long> sequenceA = makeSequence(a);
            Stack<Long> sequenceB = makeSequence(b);

            // 2 1
            // 2 1
            long c = 1;
            while (!sequenceA.isEmpty() && !sequenceB.isEmpty()) {
                long sequenceATop = sequenceA.peek();
                long sequenceBTop = sequenceB.peek();
                if (sequenceATop == sequenceBTop) {
                    c = sequenceA.pop();
                    sequenceB.pop();
                } else {
                    break;
                }

            }
            System.out.println(a + " needs " + sequenceA.size() + " steps, " + b + " needs " + sequenceB.size() + " steps, they meet at " + c);
        }

    }

    public static Stack<Long> makeSequence(long startNumber) {
        Stack<Long> sequence = new Stack<>();
        long num = startNumber;
        sequence.push(num);
        long top;
        while (true) {
            top = sequence.peek();
            if (top == 1) {
                break;
            }
            if (top % 2 == 0) {
                sequence.push(top / 2);
            } else {
                sequence.push(top * 3 + 1);
            }
        }
        return sequence;
    }
}
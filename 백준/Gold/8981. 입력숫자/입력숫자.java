import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());
        int[] after = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        Algorithm algorithm = new Algorithm(n, after);
        algorithm.printBefore();

    }

}

class Algorithm {
    private final int n;
    private final int[] after;
    private final int[] before;
    private int[] head;

    public Algorithm(int n, int[] after) {
        this.n = n;
        this.after = after;
        this.before = new int[n];
        int[] head = new int[n];
        for (int i = 0; i < n; i++) {
            head[i] = i;
        }
        this.head = head;
    }

    public void printBefore() {
        int idx = 0;
        System.out.println(n);
        for (int i : after) {
            before[idx] = i;
            head[idx] = find((idx + 1) % n);
            idx = find((idx + i) % n);
        }
        System.out.println(Arrays.stream(before).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private int find(int idx) {
        if (head[idx] == idx) {
            return idx;
        }
        return head[idx] = find(head[idx]);
    }
}
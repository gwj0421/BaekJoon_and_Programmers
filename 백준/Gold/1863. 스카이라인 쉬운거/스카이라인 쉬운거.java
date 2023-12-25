import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MAX_X = 1000000;
    private static final int MAX_Y = 500000;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Stack<Integer> buildingHeight = new Stack<>();
        int now = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            while (!buildingHeight.isEmpty() && buildingHeight.peek() > y) {
                buildingHeight.pop();
            }
            if (y == 0) {
                continue;
            }
            if (buildingHeight.isEmpty() || (!buildingHeight.isEmpty() && buildingHeight.peek() != y)) {
                buildingHeight.add(y);
                now++;
            }

        }
        System.out.println(now);

    }
}
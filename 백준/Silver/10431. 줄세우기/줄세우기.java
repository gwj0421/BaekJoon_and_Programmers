import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int p = Integer.parseInt(br.readLine());
        for (int t = 1; t < p + 1; t++) {
            st = new StringTokenizer(br.readLine());
            Queue<Integer> numbers = new LinkedList<>();
            st.nextToken();
            while (st.hasMoreTokens()) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }

            List<Integer> frontNumber = new LinkedList<>();
            int ans = 0;
            while (!numbers.isEmpty()) {
                int target = numbers.poll();
                if (frontNumber.isEmpty() || frontNumber.get(frontNumber.size() - 1) < target) {
                    frontNumber.add(target);
                } else {
                    for (int i = 0; i < frontNumber.size(); i++) {
                        if (target < frontNumber.get(i)) {
                            ans += frontNumber.size() - i;
                            frontNumber.add(i, target);
                            break;
                        }
                    }
                }
            }
            System.out.println(t + " " + ans);
        }
    }
}
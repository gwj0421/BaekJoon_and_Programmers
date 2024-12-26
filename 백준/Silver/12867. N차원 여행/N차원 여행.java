import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] indexOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        char[] dir = br.readLine().toCharArray();

        Map<Integer, Integer> cur = new HashMap<>();
        Set<Map<Integer, Integer>> visit = new HashSet<>();
        visit.add(new HashMap<>(cur));

        for (int i = 0; i < m; i++) {
            int idx = indexOrder[i];
            int change = dir[i] == '+' ? 1 : -1;
            cur.put(idx, cur.getOrDefault(idx, 0) + change);

            if (cur.get(idx) == 0) {
                cur.remove(idx);
            }

            if (!checkVisit(visit, cur)) {
                System.out.println(0);
                return;
            }
            visit.add(new HashMap<>(cur));
        }
        System.out.println(1);
    }

    public static boolean checkVisit(Set<Map<Integer, Integer>> visit, Map<Integer, Integer> cur) {
        for (Map<Integer, Integer> visitPoint : visit) {
            if (visitPoint.equals(cur)) {
                return false;
            }
        }
        return true;
    }
}



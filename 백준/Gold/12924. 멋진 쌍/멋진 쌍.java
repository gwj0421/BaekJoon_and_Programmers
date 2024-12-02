import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 12 -> 21, 13 -> 31 , 23 -> 32,
    // 123 -> 312 , 213 -> 312
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        Map<Integer, Set<Integer>> combination = new HashMap<>();

        for (int tmpX = x; tmpX <= y; tmpX++) {
            String tmp = Integer.toString(tmpX);
            combination.put(tmpX, new HashSet<>());
            for (int j = 1; j < tmp.length(); j++) {
                int tmpY = Integer.parseInt(tmp.substring(j) + tmp.substring(0, j));
                if (tmpX < tmpY && tmpY <= y) {
                    combination.get(tmpX).add(tmpY);
                }
            }
        }
        int ans = 0;
        for (Set<Integer> value : combination.values()) {
            ans += value.size();
        }
        System.out.println(ans);
    }
}



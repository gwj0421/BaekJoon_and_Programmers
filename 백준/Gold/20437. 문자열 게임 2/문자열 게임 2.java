import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Belt {
    private int durability;
    private boolean isRobot;

    public Belt(int durability, boolean isRobot) {
        this.durability = durability;
        this.isRobot = isRobot;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isRobot() {
        return isRobot;
    }

    public void upRobot() {
        this.isRobot = true;
    }

    public void downRobot() {
        this.isRobot = false;
    }

    public void downDurability() {
        this.durability--;
    }

    @Override
    public String toString() {
        return "Belt{" +
                "durability=" + durability +
                ", isRobot=" + isRobot +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());
            Map<Character, List<Integer>> distMap = makeDict(w);
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            for (Map.Entry<Character, List<Integer>> entry : distMap.entrySet()) {
                int valueSize = entry.getValue().size();
                if (valueSize >= k) {
                    for (int j = 0; j < valueSize; j++) {
                        if (j + k - 1 < valueSize) {
                            minValue = Math.min(minValue, entry.getValue().get(j + k - 1) - entry.getValue().get(j) + 1);
                            maxValue = Math.max(maxValue, entry.getValue().get(j + k - 1) - entry.getValue().get(j) + 1);
                        }
                    }
                }
            }
            if (minValue == Integer.MAX_VALUE || maxValue == Integer.MIN_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minValue + " " + maxValue);
            }
        }
    }

    public static Map<Character, List<Integer>> makeDict(String word) {
        Map<Character, List<Integer>> defaultMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            if (defaultMap.containsKey(word.charAt(i))) {
                defaultMap.get(word.charAt(i)).add(i);
            } else {
                defaultMap.put(word.charAt(i), new ArrayList<>(List.of(i)));
            }
        }
        return defaultMap;
    }


}
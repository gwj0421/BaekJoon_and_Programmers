import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

        // 최대 => MM조합에서 M을 최소 사용 MK 조합에서 M을 최대로 많이 사용
        // 최소 => MM조합에서 M을 최대한 사용 MK 조합에서 K만 사용
        String[] original = br.readLine().split("");

        List<String> group = new ArrayList<>();
        String tmp = "";
        for (String word : original) {
            if (word.equals("M")) {
                tmp += word;
            } else {
                group.add(tmp + word);
                tmp = "";
            }
        }
        if (!tmp.isEmpty()) {
            group.add(tmp);
        }
//        System.out.println(group);

        List<String> maxVal = new ArrayList<>();
        List<String> minVal = new ArrayList<>();
        for (String each : group) {
            maxVal.add(convertMax(each));
            minVal.add(convertMin(each));
        }
        System.out.println(maxVal.stream().collect(Collectors.joining()));
        System.out.println(minVal.stream().collect(Collectors.joining()));
    }

    public static String convertMax(String input) {
        if (input.charAt(input.length() - 1) == 'M') {
            return "1".repeat(input.length());
        }
        return "5" + "0".repeat(input.length() - 1);
    }

    public static String convertMin(String input) {
        if (input.charAt(input.length() - 1) == 'M') {
            return "1" + "0".repeat(input.length() - 1);
        }
        if (input.length() > 1) {
            return "1" + "0".repeat(input.length() - 2) + "5";
        }
        return "5";
    }
}
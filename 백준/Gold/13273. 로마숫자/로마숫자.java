import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Main {
    private static final Map<Integer, String> ROMA_RULE = Map.of(
            4, "IV",
            9, "IX",
            40, "XL",
            90, "XC",
            400, "CD",
            900, "CM"
    );
    private static final Map<String, Integer> ARABIA_RULE = Map.of(
            "IV",4,
            "IX",9,
            "XL",40,
            "XC",90,
            "CD",400,
            "CM",900
    );
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String input = br.readLine();
            if (Character.isDigit(input.charAt(0))) {
                System.out.println(convert2Roma(input));
            } else {
                System.out.println(convert2Arabia(input));
            }
        }
    }

    public static String convert2Roma(String input) {
        int[] num = Arrays.stream(input.split("")).mapToInt(Integer::parseInt).toArray();
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (num[i] != 0) {
                tmp.add(num[i] * (int) Math.pow(10, input.length() - 1 - i));
            }
        }
        StringBuilder result = new StringBuilder();
        for (Integer i : tmp) {
            if (ROMA_RULE.containsKey(i)) {
                result.append(ROMA_RULE.get(i));
            } else {
                if (i >= 1000) {
                    result.append("M".repeat(i / 1000));
                } else if (i >= 500) {
                    result.append("D" + "C".repeat((i - 500) / 100));
                } else if (i >= 100) {
                    result.append("C".repeat(i / 100));
                } else if (i >= 50) {
                    result.append("L" + "X".repeat((i - 50) / 10));
                } else if (i >= 10) {
                    result.append("X".repeat(i / 10));
                } else if (i >= 5) {
                    result.append("V" + "I".repeat((i - 5)));
                } else {
                    result.append("I".repeat(i));
                }
            }
        }
        return result.toString();
    }
    // I = 1
    // V = 5
    // X = 10
    // L = 50
    // C = 100
    // D = 500
    // M = 1000
    // 100 + 1000

    public static int convert2Arabia(String input) {
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i < input.length() - 1 && ARABIA_RULE.containsKey(input.substring(i, i + 2))) {
                num += ARABIA_RULE.get(input.substring(i, i + 2));
                i++;
            } else {
                switch (input.charAt(i)) {
                    case 'I':
                        num += 1;
                        break;
                    case 'V':
                        num += 5;
                        break;
                    case 'X':
                        num += 10;
                        break;
                    case 'L':
                        num += 50;
                        break;
                    case 'C':
                        num += 100;
                        break;
                    case 'D':
                        num += 500;
                        break;
                    case 'M':
                        num += 1000;
                        break;
                }
            }
        }
        return num;
    }

}
import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] numberStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numberStr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(numberStr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        StringBuilder sb = new StringBuilder();
        for (String num : numberStr) {
            sb.append(num);
        }
        if (sb.toString().charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final Map<Integer, Integer> scoreConvert = Map.of(1, 3, 2, 2, 3, 1, 5, 1, 6, 2, 7, 3);
    private static final Map<Integer, String[]> order = Map.of(0, new String[]{"R", "T"},1, new String[]{"C", "F"},2, new String[]{"J", "M"},3, new String[]{"A", "N"});
    public String solution(String[] surveys, int[] choices) {
        Map<String, Integer> result = new HashMap<>(Map.of("R", 0, "T", 0, "C", 0, "F", 0, "J", 0, "M", 0, "A", 0, "N", 0));
        for (int i = 0; i < surveys.length; i++) {
            calculate(surveys[i].split(""),choices[i],result);
        }
        return returnResult(result);
    }

    private void calculate(String[] personality, int choice, Map<String, Integer> result) {
        if (choice < 4) {
            result.put(personality[0], result.get(personality[0]) + scoreConvert.get(choice));
        } else if (choice > 4) {
            result.put(personality[1], result.get(personality[1]) + scoreConvert.get(choice));
        }
    }

    private String returnResult(Map<String, Integer> result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String[] compareTarget = order.get(i);
            if (result.get(compareTarget[0]) >= result.get(compareTarget[1])) {
                sb.append(compareTarget[0]);
            } else {
                sb.append(compareTarget[1]);
            }
        }
        return sb.toString();
    }

}
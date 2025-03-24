import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] split = term.split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]));
        }

        Date now = new Date(today);
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            Date expireDate = new Date(split[0]);
            expireDate.plusMonth(termMap.get(split[1]));
            if (now.compareTo(expireDate) >= 0) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Date implements Comparable<Date>{
    private int y;
    private int m;
    private int d;

    public Date(String input) {
        String[] split = input.split("\\.");
        this.y = Integer.parseInt(split[0]);
        this.m = Integer.parseInt(split[1]);
        this.d = Integer.parseInt(split[2]);
    }

    public void plusMonth(int month) {
        while (m + month > 12) {
            y++;
            month -= 12;
        }
        m += month;
    }
    public int getValue() {
        return d + m * 100 + y * 10000;
    }

    @Override
    public int compareTo(Date o) {
        return Integer.compare(getValue(), o.getValue());
    }
}
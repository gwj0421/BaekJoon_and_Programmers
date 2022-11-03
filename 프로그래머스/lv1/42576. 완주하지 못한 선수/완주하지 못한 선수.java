import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, Integer> checkPeople(String[] peoples) {
        Map<String, Integer> result = new HashMap<>();
        for (String p : peoples) {
            if (result.containsKey(p)) {
                result.replace(p, result.get(p) + 1);
            } else {
                result.put(p, 1);
            }
        }
        return result;
    }
    String WhoIsLastOne(Map<String,Integer> allParticipants,Map<String,Integer> allCompletion) {
        for (Map.Entry<String, Integer> entry : allParticipants.entrySet()) {
            String people_name = entry.getKey();
            Integer people_cnt = entry.getValue();
            if (!allCompletion.containsKey(people_name)) {
                return people_name;
            } else if (people_cnt - allCompletion.get(people_name) != 0) {
                return people_name;
            }
        }
        return "-1";
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String,Integer> allParticipants = checkPeople(participant);
        Map<String, Integer> allCompletion = checkPeople(completion);
        answer = WhoIsLastOne(allParticipants, allCompletion);
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] a1 = {"leo", "kiki", "eden"};
        String[] a2 = {"eden", "kiki"};
        System.out.println(s.solution(a1,a2));
    }
}
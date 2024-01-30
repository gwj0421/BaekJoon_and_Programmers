import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Person {
    private final String name;
    private Map<String, Integer> sendInfo;
    private int presentScore;

    public Person(String name,String[] friends) {
        this.name = name;
        this.sendInfo = makeEmptySendInfo(name,friends);
        this.presentScore = 0;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSendInfo() {
        return sendInfo;
    }

    public int getPresentScore() {
        return presentScore;
    }

    private Map<String, Integer> makeEmptySendInfo(String name, String[] friends) {
        Map<String, Integer> emptySendInfo = new HashMap<>();
        for (String friend : friends) {
            if (!name.equals(friend)) {
                emptySendInfo.put(friend, 0);
            } else {
                emptySendInfo.put(name, -1);
            }
        }
        return emptySendInfo;
    }

    public void send(String receiveName) {
        getSendInfo().put(receiveName, getSendInfo().getOrDefault(receiveName, 0) + 1);
    }

    public void sendPresentScore() {
        this.presentScore++;
    }

    public void receivedPresentScore() {
        this.presentScore--;
    }

    public int receivedPresentCnt(Map<String, Person> peopleTable) {
        int cnt = 0;
        for (Map.Entry<String, Person> entry : peopleTable.entrySet()) {
            if (!entry.getKey().equals(getName())) {
                if (getSendInfo().get(entry.getKey()) > entry.getValue().getSendInfo().get(getName())) {
                    cnt++;
                } else if (getSendInfo().get(entry.getKey()) == entry.getValue().getSendInfo().get(getName())) {
                    if (getPresentScore() > entry.getValue().getPresentScore()) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Person> present = new HashMap<>();
        for (String friend : friends) {
            present.put(friend, new Person(friend,friends));
        }
        for (String gift : gifts) {
            String[] presentInfo = gift.split(" ");
            present.get(presentInfo[0]).send(presentInfo[1]);
            present.get(presentInfo[0]).sendPresentScore();
            present.get(presentInfo[1]).receivedPresentScore();
        }
        int maxValue = 0;
        for (Person person : present.values()) {
            maxValue = Math.max(maxValue, person.receivedPresentCnt(present));
        }
        return maxValue;
    }
}
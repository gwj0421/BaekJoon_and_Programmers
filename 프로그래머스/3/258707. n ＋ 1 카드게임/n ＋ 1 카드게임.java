import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int target = n + 1;
        int idx = n / 3;
        Set<Integer> hand = new HashSet<>();
        Set<Integer> addition = new HashSet<>();
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }
        while (true) {
            answer++;
            if (idx >= n) {
                break;
            }
            for (int i = 0; i < 2; i++) {
                addition.add(cards[idx++]);
            }
            boolean flag = false;
            for (int handNum : hand) {
                if (hand.contains(target - handNum)) {
                    hand.remove(handNum);
                    hand.remove(target - handNum);
                    flag = true;
                    break;
                }
            }
            if (!flag && coin > 0) {
                for (int handNum : hand) {
                    if (addition.contains(target - handNum)) {
                        hand.remove(handNum);
                        addition.remove(target - handNum);
                        coin--;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag && coin > 1) {
                for (int additionNum : addition) {
                    if (addition.contains(target - additionNum)) {
                        addition.remove(additionNum);
                        addition.remove(target - additionNum);
                        coin -= 2;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        return answer;
    }
}
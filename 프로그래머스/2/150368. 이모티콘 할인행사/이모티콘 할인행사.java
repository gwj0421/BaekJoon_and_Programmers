class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        Algorithm algorithm = new Algorithm(users, emoticons);
        return algorithm.solve();
    }
}

class Algorithm {
    private static final int[] DISCOUNT_RATE = new int[]{40, 30, 20, 10};
    private int[][] users;
    private int[] emoticons;
    private int[] answer;

    public Algorithm(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.answer = new int[2];
    }

    public int[] solve() {
        setDiscount(0, new int[emoticons.length]);
        return answer;
    }

    private void setDiscount(int idx, int[] discount) {
        if (idx >= discount.length) {
            int[] tmp = new int[2];
            for (int[] user : users) {
                int pay = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (user[0] <= discount[i]) {
                        pay += getPrice(emoticons[i], discount[i]);
                    }
                }
                if (user[1] <= pay) {
                    tmp[0]++;
                } else {
                    tmp[1] += pay;
                }
            }

            if ((answer[0] < tmp[0]) || (answer[0] == tmp[0] && answer[1] < tmp[1])) {
                answer = new int[]{tmp[0], tmp[1]};
            }
            return;
        }
        for (int discountRate : DISCOUNT_RATE) {
            discount[idx] = discountRate;
            setDiscount(idx + 1, discount);
        }
    }

    private int getPrice(int price, int discountRate) {
        return price * (100 - discountRate) / 100;
    }
}
class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
    }
}
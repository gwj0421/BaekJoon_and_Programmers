import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Number {
        private final int idx;
        private final int total;

        public Number(int idx, int total) {
            this.idx = idx;
            this.total = total;
        }

        public int getIdx() {
            return idx;
        }

        public int getTotal() {
            return total;
        }
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;

        Queue<Number> queue = new LinkedList<>();
        queue.add(new Number(0, numbers[0]));
        queue.add(new Number(0, -numbers[0]));
        while (!queue.isEmpty()) {
            Number number = queue.poll();
            if (number.getIdx() == numbers.length - 1 && number.getTotal() == target) {
                answer++;
                continue;
            }
            int nextIdx = number.getIdx() + 1;
            if (nextIdx < numbers.length) {
                queue.add(new Number(nextIdx, number.getTotal() + numbers[nextIdx]));
                queue.add(new Number(nextIdx, number.getTotal() - numbers[nextIdx]));
            }

        }

        return answer;
    }
}
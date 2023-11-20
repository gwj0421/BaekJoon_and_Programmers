import java.util.*;

class Solution {
    class Operation {
        private final int start;
        private final int end;

        public Operation(int[] info) {
            this.start = info[0];
            this.end = info[1];
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    public int solution(int[][] jobs) {
        int answer = 0;
        int cnt = 0;
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Operation> heap = new PriorityQueue<>((o1, o2) -> {
            if (o1.getEnd() < o2.getEnd()) {
                return -1;
            }
            return 1;
        });

        int jobIdx = 0;
        int end = 0;
        while (cnt < jobs.length) {
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= end) {
                heap.add(new Operation(jobs[jobIdx++]));
            }
            if (heap.isEmpty()) {
                end = jobs[jobIdx][0];
            } else {
                Operation temp = heap.poll();
                answer += temp.getEnd() + end - temp.getStart();
                end += temp.getEnd();
                cnt++;
            }
        }

        return answer / jobs.length;
    }
}
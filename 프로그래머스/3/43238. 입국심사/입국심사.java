import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long start = 1;
        long end = (long) times[times.length - 1] * n;

        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }
            if (sum < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = mid;
            } 
        }

        return answer;
    }
}
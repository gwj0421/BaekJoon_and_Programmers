import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;

        while (start <= end) {
            if (people[start] + people[end] <= limit) {
                start++;
            }
            end--;
            ans++;
        }
        return ans;
    }
}
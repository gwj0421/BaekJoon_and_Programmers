import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Set<Integer> pkm = new HashSet<>();
        for (Integer integer : nums) {
            pkm.add(integer);
        }
        answer = pkm.size();
        if (answer > nums.length/2) {
            answer = nums.length/2;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a1 = {3,3,3,2,2,4};
        System.out.println(s.solution(a1));
    }
}
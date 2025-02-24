class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        for (int i = 0; i < n; i++) {
            int h = schedules[i] / 100;
            int m = schedules[i] % 100 + 10;
            if (m > 59) {
                h++;
                m %= 60;
            }
            int time = h * 100 + m;
            boolean flag = true;
            for (int j = 0; j < timelogs[i].length; j++) {
                if ((startday - 1 + j) % 7 < 5 && timelogs[i][j] > time) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
            }
        }
        return answer;
    }
}

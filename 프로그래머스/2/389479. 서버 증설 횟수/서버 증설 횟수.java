class Solution {
    public int solution(int[] players, int m, int k) {
        int n = players.length;
        int[] ServerCnt = new int[n];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ServerCnt[i] < players[i] / m) {
                int tmp = players[i] / m - ServerCnt[i];
                cnt += tmp;
                for (int j = i; j < Math.min(i + k, n); j++) {
                    ServerCnt[j] += tmp;
                }
            }
        }
        return cnt;
    }
}
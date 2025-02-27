class Solution {
    public int solution(int n, int w, int num) {
        if (n % w == 0) {
            return n / w - (num - 1) / w;
        } else {
            int idx = getHeight(num, w);
            int lastIdx = getHeight(n, w);
            if ((n / w % 2 == 0 && idx <= lastIdx) || (n / w % 2 == 1 && lastIdx <= idx)) {
                return n / w + 1 - (num - 1) / w;
            }
            return n / w - (num - 1) / w;
        }
    }

    public int getHeight(int num, int width) {
        num--;
        return num / width % 2 == 0 ? num % width : width - 1 - num % width;
    }
}
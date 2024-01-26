class Solution {
    fun solution(a: Int, b: Int): Int {
        var cnt = 2
        var top: Int = a
        var bottom: Int = b
        while (b >= cnt) {
            if (top % cnt == 0 && bottom % cnt == 0) {
                top /= cnt
                bottom /= cnt
            } else if (bottom % cnt == 0) {
                bottom /= cnt
                if (cnt != 2 && cnt != 5) {
                    return 2
                }
            } else {
                cnt++
            }
        }
        return 1
    }
}
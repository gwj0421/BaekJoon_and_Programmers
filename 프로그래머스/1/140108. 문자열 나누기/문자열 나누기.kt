class Solution {
    fun solution(s: String): Int {
        var answer: Int = 1
        var sameCnt: Int = 1
        var diffCnt: Int = 0
        var stand: Char? = s.getOrNull(0)
        for (i in 1..s.lastIndex) {
            if (sameCnt == 0) {
                stand = s[i]
                sameCnt++
                answer++
                continue
            } else {
                if (s[i] == stand) {
                    sameCnt++
                } else {
                    diffCnt++
                }
            }

            if (sameCnt == diffCnt) {
                sameCnt = 0
                diffCnt = 0
            }
        }
        return answer
    }
}

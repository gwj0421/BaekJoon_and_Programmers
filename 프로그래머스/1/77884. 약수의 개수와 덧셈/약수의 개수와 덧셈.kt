import kotlin.math.sqrt

class Solution {
    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0
        for (num in left..right) {
            var cnt: Int = 0
            var line:Int = sqrt(num.toFloat()).toInt()
            for (i in 1..line-1) {
                if (num % i == 0) {
                    cnt += 2
                }
            }
            if (line * line == num) {
                cnt++
            }
            if (cnt % 2 == 0) {
                answer += num
            } else {
                answer -= num
            }
        }
        return answer
    }

}
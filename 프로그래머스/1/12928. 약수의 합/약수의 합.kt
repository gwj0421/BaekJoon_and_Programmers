import kotlin.math.sqrt

class Solution {
    fun solution(n: Int): Int {
        var answer = 0
        val stand = sqrt(n.toFloat()).toInt()
        for (i in 1..stand) {
            if (n % i == 0) {
                answer += i + n / i
            }
        }
        if (stand * stand == n) {
            answer -= stand
        }
        return answer
    }
}
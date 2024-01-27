class Solution {
    fun solution(n: Int): Int {
        var answer = 0
        var num:Int = n
        while (num > 0) {
            answer += num %10
            num /= 10
        }
        return answer
    }
}
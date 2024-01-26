class Solution {
    fun solution(n: Int): String {
        var answer = ""
        for (i in 1..n / 2) {
            answer += "수박"
        }
        if (n % 2 == 1) {
            answer+="수"
        }
        
        return answer
    }
}
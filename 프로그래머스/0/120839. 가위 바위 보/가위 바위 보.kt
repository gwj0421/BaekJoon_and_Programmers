class Solution {
    fun solution(rsp: String): String {
        var answer: String = ""
        val winPrinciple = mapOf("2" to "0","0" to "5","5" to "2")
        for (x in rsp) {
            answer += winPrinciple.get(x.toString())
        }
        return answer
    }
}
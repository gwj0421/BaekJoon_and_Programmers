class Solution {
    fun solution(number: String): Int {
        var answer: Int = 0
        for (x in number) {
            answer += Character.getNumericValue(x)
        }
        return answer % 9
    }
}
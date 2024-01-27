class Solution {
    fun solution(numbers: IntArray): Int {
        var answer: Int = 0
        val inNum = BooleanArray(10) { true }

        for (number in numbers) {
            inNum[number] = false
        }
        println(inNum.contentToString())
        for (i in 0..9) {
            if (inNum[i]) {
                answer += i
            }
        }
        return answer
    }
}
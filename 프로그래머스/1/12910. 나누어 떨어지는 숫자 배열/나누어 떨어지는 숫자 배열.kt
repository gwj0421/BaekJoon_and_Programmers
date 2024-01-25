class Solution {
    fun solution(arr: IntArray, divisor: Int): IntArray {
        val answer = arrayListOf<Int>()

        for (num in arr) {
            if (num % divisor == 0) {
                answer.add(num)
            }
        }
        if (answer.isEmpty()) {
            return intArrayOf(-1)
        }
        answer.sort()
        return answer.toIntArray()
    }
}
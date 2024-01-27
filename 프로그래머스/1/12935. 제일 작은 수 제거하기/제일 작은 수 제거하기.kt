import java.util.*

class Solution {
    fun solution(arr: IntArray): IntArray {
        var answer:MutableList<Int> = LinkedList()
        var minValue: Int = Int.MAX_VALUE
        var minIdx: Int = -1
        for (i in 0..arr.lastIndex) {
            answer.add(arr[i])
            if (arr[i] < minValue) {
                minValue = arr[i]
                minIdx = i
            }
        }
        answer.removeAt(minIdx)
        if (answer.isEmpty()) {
            return intArrayOf(-1)
        } else {
            return answer.toIntArray()
        }
    }
}
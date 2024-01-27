import java.util.*

class Solution {
    fun solution(n: Long): Long {
        var answer: Long = 0
        val pq: PriorityQueue<Long> = PriorityQueue(compareBy { -it })
        var num:Long = n
        while (num > 0) {
            pq.add(num % 10)
            num /= 10
        }
        while (!pq.isEmpty()) {
            answer = answer * 10 + pq.poll()
        }
        return answer
    }
}
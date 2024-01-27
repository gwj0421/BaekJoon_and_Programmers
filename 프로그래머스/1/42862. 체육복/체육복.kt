class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = 0
        // 0 = 없음, 1 = 있음, 2 = 기부
        var people: IntArray = IntArray(n+1) { 1 }
        people[0] = -1
        for (lostIdx in lost) {
            people[lostIdx] = 0
        }
        for (reserveIdx in reserve) {
            if (people[reserveIdx] == 0) {
                people[reserveIdx] = 1
            } else {
                people[reserveIdx] = 2
            }
        }
        for (i in 1..n) {
            if (people[i] == 0) {
                if (i > 1 && people[i - 1] == 2) {
                    people[i - 1] = 1
                    people[i] = 1
                } else if (i < n && people[i + 1] == 2) {
                    people[i + 1] = 1
                    people[i] = 1
                }
            }
        }
        for (person in people) {
            if (person > 0) {
                answer++
            }
        }
        return answer
    }
}
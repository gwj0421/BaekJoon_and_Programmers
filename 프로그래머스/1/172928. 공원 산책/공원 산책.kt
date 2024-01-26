class Solution {
    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        val move = mapOf(
            "E" to intArrayOf(0, 1),
            "W" to intArrayOf(0, -1),
            "S" to intArrayOf(1, 0),
            "N" to intArrayOf(-1, 0)
        )
        var n: Int = park.size - 1
        var m: Int = park[0].length - 1

        var ry: Int = 0
        var rx: Int = 0

        for (i in 0..n) {
            for (j in 0..m) {
                if (park[i][j] == 'S') {
                    ry = i
                    rx = j
                }
            }
        }
        for (route in routes) {
            val info = route.split(" ")
            var nextY: Int = ry
            var nextX: Int = rx
            var canGo: Boolean = true
            for (i in 1..info[1].toInt()) {
                nextY += move.get(info[0])!![0]
                nextX += move.get(info[0])!![1]
                if (nextY !in 0..n || nextX !in 0..m || park[nextY][nextX] == 'X') {
                    canGo = false
                    break
                }
            }
            if (canGo) {
                ry = nextY
                rx = nextX
            }
        }
        return intArrayOf(ry, rx)
    }
}
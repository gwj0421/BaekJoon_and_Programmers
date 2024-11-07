package org.example

fun main() = with(System.`in`.bufferedReader()) {
    val (r, c, k) = readLine().split(" ").map { it.toInt() }
    val board: Array<CharArray> = Array(r, { CharArray(c) })
    for (i in 0..r - 1) {
        board[i] = readLine().toCharArray()
    }

    Solution(r, c, k, board).solve()
}

class Solution(val r: Int, val c: Int, val k: Int, val board: Array<CharArray>) {
    companion object {
        val MOVE_PATTERN: Array<IntArray> = arrayOf(
            intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0)
        )
    }

    var cnt: Int = 0
    fun solve() {
        val visit = Array(r, { BooleanArray(c) })
        visit[r - 1][0] = true
        dfs(r - 1, 0, 1, visit)
        println(cnt)
    }

    fun dfs(y: Int, x: Int, moveCnt: Int, visit: Array<BooleanArray>) {
        if (y == 0 && x == c - 1) {
            if (moveCnt == k) {
                cnt++
            }
            return
        }

        for (d in MOVE_PATTERN.indices) {
            val ny: Int = y + MOVE_PATTERN[d][0]
            val nx: Int = x + MOVE_PATTERN[d][1]
            if (-1 < ny && ny < r && -1 < nx && nx < c && !visit[ny][nx] && board[ny][nx] != 'T') {
                visit[ny][nx] = true
                dfs(ny, nx, moveCnt + 1, visit)
                visit[ny][nx] = false
            }

        }
    }
}

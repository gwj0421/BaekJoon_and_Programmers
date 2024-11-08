package org.example

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val board = readLine()
    val word = readLine()
    Solution(n, m, board, word).solve()
}

class Solution(val n: Int, val m: Int, val board: String, val word: String) {
    val dp = Array(n, { IntArray(m, { -1 }) })

    var ans = 0
    fun solve() {
        for (i in 0..n - 1) {
            dfs(i, 0)
        }
        for (i in 0..n - 1) {
            ans = max(ans, dp[i][0])

        }
        println(ans)
    }

    fun dfs(boardIdx: Int, wordIdx: Int): Int {
        if (wordIdx == m) {
            return 0;
        }

        if (dp[boardIdx][wordIdx] > -1) {
            return dp[boardIdx][wordIdx]
        }

        var point = 0
        if (boardIdx > 0) {
            point = max(point, dfs(boardIdx - 1, wordIdx + 1))
        }
        if (boardIdx < n - 1) {
            point = max(point, dfs(boardIdx + 1, wordIdx + 1))
        }
        if (board[boardIdx] == word[wordIdx]) {
            point++
        }
        return point.also { dp[boardIdx][wordIdx] = it }
    }

}

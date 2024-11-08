package org.example

import kotlin.math.min

fun main() 
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
= with(System.`in`.bufferedReader())
{
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val board = Array(n, { IntArray(m) })
    for (i in 0..n - 1) {
        board[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val dp = Array(n, { Array(m, { mutableMapOf(-1 to 100001, 0 to 100001, 1 to 100001) }) })

    for (i in 0..m - 1) {
        for (j in -1..1) {
            if (-1 < i + j && i + j < m) {
                dp[1][i][j] = board[0][i + j]
            }
        }
    }
//    for (mutableMaps in dp) {
//        for (mutableMap in mutableMaps) {
//            println(mutableMap.toString())
//        }
//        println()
//    }
    for (i in 2..n - 1) {
        for (j in 0..m - 1) {
            for (beforeDir in -1..1) {
                for (nowDir in -1..1) {
                    if (-1 < j + nowDir && j + nowDir < m &&
                        -1< j + nowDir + beforeDir && j + nowDir + beforeDir < m &&
                        beforeDir != nowDir
                    ) {
                        dp[i][j][nowDir] = min(
                            dp[i][j][nowDir]!!,
                            (dp[i - 1][j + nowDir][beforeDir]!! + board[i-1][j+nowDir])
                        )

                    }
                }
            }
        }
    }

    var ans: Int = Int.MAX_VALUE

    for (i in 0..m - 1) {
        for (info in dp[n - 1][i]) {
            ans = min(ans, info.value + board[n - 1][i])
        }
    }
    println(ans)


}

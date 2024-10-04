package org.example

import java.io.BufferedReader
import java.io.FileReader
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min

fun main()
= with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val mod: Int = 1_000_000_007

    val n = readLine().toInt()+1

    val dp = Array(1002, { LongArray(2) })
    dp[0][1] = 1
    dp[1][0] = 1
    dp[2][0] = 1
    dp[3] = longArrayOf(2, 1)

    for (i: Int in 4..n) {
        dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0]) % mod
        dp[i][1] = (dp[i - 1][1] + dp[i - 2][0] + dp[i - 2][1] * 2 + dp[i - 3][0] * 2 + dp[i - 3][1] * 3) % mod
    }
    println(dp[n][1])
}
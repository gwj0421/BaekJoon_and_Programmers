package org.example

import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.min
import kotlin.math.sqrt

fun main()
= with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val canJump: BooleanArray = BooleanArray(n + 1, { true })
    for (i: Int in 1..m) {
        canJump[readLine().toInt()] = false
    }

    // 1,2,4,7,11,16,22
    val dp: Array<IntArray> = Array(n + 1, { IntArray(getMaxJumpStep(n) + 2, { 10001 }) })

    dp[1][0]=0
    for (arrivalIdx: Int in 2..n) {
        if (canJump[arrivalIdx]) {
            for (step: Int in 1..getMaxJumpStep(arrivalIdx)) {
                dp[arrivalIdx][step] = min(
                    dp[arrivalIdx - step][step],
                    min(
                        dp[arrivalIdx - step][step - 1], dp[arrivalIdx - step][step + 1]
                    )
                ) + 1
            }
        }
    }
    val ans = dp[n].min()
    if (ans == 10001) {
        println(-1)
    } else {
        println(ans)
    }

}

fun getMaxJumpStep(N: Int): Int {
    return sqrt((2 * N).toDouble()).toInt()

}
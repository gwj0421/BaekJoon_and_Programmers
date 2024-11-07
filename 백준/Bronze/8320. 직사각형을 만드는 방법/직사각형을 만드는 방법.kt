package org.example

import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader())
{
    val n: Int = readLine().toInt()
    var ans: Int = 0
    for (i: Int in 1..sqrt(n.toDouble()).toInt()) {
        for (j: Int in i..n) {
            if (i * j <= n) {
                ans++
            } else {
                break
            }
        }
    }
    println(ans)

}
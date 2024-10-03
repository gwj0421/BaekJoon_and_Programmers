package org.example

import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
        = with(System.`in`.bufferedReader())
{
    val t: Int = readLine().toInt()
    for (i: Int in 1..t) {
        val (n, s, e) = readLine().split(" ").map { it.toInt() }
        if ((s == 1 && e == n) || (s == n && e == 1)) {
            println(0)
            continue
        }
        if (s == 1 || s == n || abs(s - e) == 1) {
            println(1)
        } else {
            println(2)
        }
    }

}

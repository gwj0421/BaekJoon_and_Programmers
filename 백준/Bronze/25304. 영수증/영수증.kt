package org.example

import java.io.BufferedReader
import java.io.FileReader

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
= with(System.`in`.bufferedReader())
{
//    val br = BufferedReader(FileReader("./src/main/resources/input.txt"))

    var x = readLine().toInt()
    val n = readLine().toInt()
    for (i: Int in 1..n) {
        val priceInfo = readLine().split(" ").map { it.toInt() }
        x -= priceInfo[0] * priceInfo[1]
    }

    if (x == 0) {
        println("Yes")
        return
    }
    println("No")

}
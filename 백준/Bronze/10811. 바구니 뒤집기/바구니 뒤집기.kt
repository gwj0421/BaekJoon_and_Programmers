package org.example

import java.io.BufferedReader
import java.io.FileReader
import java.util.StringTokenizer

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
        = with(System.`in`.bufferedReader())
{
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(n) { it + 1 }

    repeat(m) {
        val (i,j) = readLine().split(" ").map { it.toInt() }
        arr.reverse(i-1, j)
    }


    println(arr.joinToString(" "))
}
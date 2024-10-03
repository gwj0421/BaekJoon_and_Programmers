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
    val n = readLine().toInt()
    val chickenBoard = Array(n, { IntArray(n) })
    for (i: Int in chickenBoard.indices) {
        chickenBoard[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val subtotal = Array(n + 1, { IntArray(n + 1) })
    for (i: Int in 1..n) {
        for (j: Int in 1..n) {
            subtotal[i][j] += subtotal[i - 1][j] + subtotal[i][j - 1] -subtotal[i-1][j-1]+ chickenBoard[i - 1][j - 1]
        }
    }

    val q = readLine().toInt()
    for (i: Int in 1..q) {
        val (r1, c1, r2, c2) = readLine().split(" ").map { it.toInt() }
        // A + B = C
        // B - A = ? => 2B - C
        println(2 * getArea(r1 + 1, c1 + 1, r2 - 1, c2 - 1, subtotal) - getArea(r1, c1, r2, c2, subtotal))
    }
}

fun getArea(y1: Int, x1: Int, y2: Int, x2: Int, subtotal: Array<IntArray>): Int {
    return subtotal[y2][x2] - subtotal[y2][x1 - 1] - subtotal[y1 - 1][x2] + subtotal[y1 - 1][x1 - 1]
}

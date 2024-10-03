package org.example

import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader())
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (h, w) = readLine().split(" ").map { it.toInt() }
    var ans: Int = 0
    val board: Array<CharArray> = Array(h, { CharArray(w) })
    for (i: Int in board.indices) {
        board[i] = readLine().toCharArray()
    }
    for (i: Int in 0..h - 1) {
        var inArea: Boolean = false
        for (j: Int in 0..w - 1) {
            if (inArea) {
                if (board[i][j] != '.') {
                    inArea = !inArea
                }
                ans++
            } else if (board[i][j] != '.') {
                inArea = !inArea
            }
        }
    }
    println(ans)

}

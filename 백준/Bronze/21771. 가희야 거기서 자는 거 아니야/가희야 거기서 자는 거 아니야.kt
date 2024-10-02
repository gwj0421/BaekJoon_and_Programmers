package org.example

import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.max
import kotlin.math.min

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
        = with(System.`in`.bufferedReader())
{
    val (r, c) = readLine().split(" ").map { it.toInt() }
    val (rg, cg, rp, cp) = readLine().split(" ").map { it.toInt() }
    val board: Array<CharArray> = Array(r, { CharArray(c) })

    var dogArea: Int = rg * cg
    var pillowArea: Int = rp * cp

    for (i: Int in 1..r) {
        val line = readLine().toCharArray()
        for (j: Int in 0..c - 1) {
            if (line[j] == 'G') {
                dogArea--
            } else if (line[j] == 'P') {
                pillowArea--
            }
        }
    }
    if (pillowArea > 0) {
        println(1)
        return@with
    }
    println(0)
}

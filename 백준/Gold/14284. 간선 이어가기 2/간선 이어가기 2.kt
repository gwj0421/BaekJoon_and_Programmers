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

fun main() = with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val relationship: MutableMap<Int, MutableList<IntArray>> = HashMap()
    for (i: Int in 1..n) {
        relationship.put(i, mutableListOf())
    }
    for (i: Int in 1..m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        relationship.get(a)!!.add(intArrayOf(b, c))
        relationship.get(b)!!.add(intArrayOf(a, c))
    }
    val (s, t) = readLine().split(" ").map { it.toInt() }
    var costTable: IntArray = IntArray(n + 1, { Int.MAX_VALUE })
    costTable[s] = 0
    val queue: PriorityQueue<IntArray> = PriorityQueue<IntArray>(compareBy { it[1] })

    queue.add(intArrayOf(s, 0))
    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now[1] > costTable[now[0]]) {
            continue
        }
        for (next in relationship.get(now[0])!!) {
            if (now[1] + next[1] < costTable[next[0]]) {
                costTable[next[0]] = now[1] + next[1]
                queue.add(intArrayOf(next[0], now[1] + next[1]))
            }
        }
    }
    println(costTable[t])

}

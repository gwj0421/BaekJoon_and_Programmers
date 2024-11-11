package org.example

import kotlin.math.max
import kotlin.math.min

fun main()
= with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (n, m, t) = readLine().split(" ").map { it.toInt() }

    val rel: MutableMap<Int, MutableList<Edge>> = mutableMapOf()
    for (i in 1..n) {
        rel.put(i, mutableListOf())
    }

    val costTable = Array(n+1, { IntArray(n+1, { Int.MAX_VALUE }) })
    repeat(m) {
        val (u, v, h) = readLine().split(" ").map { it.toInt() }
        rel.get(u)!!.add(Edge(v, h))
        costTable[u][v] = h
    }
    for (mid in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                if (s != mid && e != mid && s != e) {
                    costTable[s][e] = min(costTable[s][e], max(costTable[s][mid], costTable[mid][e]))
                }
            }
        }
    }





    repeat(t) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        if (costTable[s][e] == Int.MAX_VALUE) {
            println(-1)
        } else {
            println(costTable[s][e])
        }
    }
}

class Edge(val en: Int, val cost: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return compareValues(this.cost, other.cost)
    }
}

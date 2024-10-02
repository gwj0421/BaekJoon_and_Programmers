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
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val houses = Array(n, { IntArray(n) })
    for (i: Int in houses.indices) {
        houses[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    Algorithm(n, k, houses).search()

}

class Algorithm(val n: Int, val k: Int, val houses: Array<IntArray>) {
    var ans: Int = 200001

    fun search() {
        dfs(0, BooleanArray(n))
        println(ans)
    }

    fun dfs(depth: Int, visit: BooleanArray) {
        if (depth == k) {
            getMinDist(visit)
            return
        }

        for (i: Int in houses.indices) {
            if (!visit[i]) {
                visit[i] = true
                dfs(depth + 1, visit)
                visit[i] = false
            }
        }

    }

    fun getMinDist(visit: BooleanArray) {
        val minDist = IntArray(n, { 200001 })
        for (i: Int in visit.indices) {
            if (visit[i]) {
                minDist[i] = 0
                for (j: Int in 0..n - 1) {
                    if (i != j) {
                        minDist[j] = min(minDist[j], getDist(i, j))
                    }
                }
            }
        }

        ans = min(ans, minDist.max())
    }

    fun getDist(idx1: Int, idx2: Int): Int {
        return abs(houses[idx1][0] - houses[idx2][0]) + abs(houses[idx1][1] - houses[idx2][1])
    }
}


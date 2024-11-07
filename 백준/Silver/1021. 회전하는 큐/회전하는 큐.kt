package org.example

import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    var queue: Queue<Int> = LinkedList()
    for (i in 1..n) {
        queue.add(i)
    }

    var ans: Int = 0
    val st: StringTokenizer = StringTokenizer(readLine())
    var dist: Int
    while (st.hasMoreTokens()) {
        val target: Int = st.nextToken().toInt()
        dist = 0
        while (queue.peek() != target) {
            queue.add(queue.poll())
            dist++
        }
        ans += min(dist, queue.size - dist)
        queue.poll()
    }
    println(ans)
}
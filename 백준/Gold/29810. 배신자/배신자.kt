package org.example

import kotlin.math.max

fun main()
= with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (n, m) = readLine().split(" ").map { it.toInt() }

//    val rel: MutableMap<Int, MutableList<Int>> = mutableMapOf()
//    for (k in 1..n) {
//        rel.put(k, mutableListOf())
//    }
    val rel: Array<IntArray> = Array(m, { IntArray(2) })

    repeat(m) { idx ->
//        rel.get(p1)!!.add(p2)
//        rel.get(p2)!!.add(p1)
        rel[idx] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    val betrayer: Int = readLine().toInt()
    if (n < 2) {
        println(1)
        return
    }
    val head: IntArray = IntArray(n + 1, { idx -> idx })
    val betrayerFriends: MutableList<Int> = mutableListOf()

    for (i: Int in rel.indices) {
        if (rel[i][0] != betrayer && rel[i][1] != betrayer) {
            union(head, rel[i][0], rel[i][1])
        } else {
            if (rel[i][0] == betrayer) {
                betrayerFriends.add(rel[i][1])
            } else {
                betrayerFriends.add(rel[i][0])
            }
        }
    }
    val groupCnt: IntArray = IntArray(n + 1)
    for (i: Int in 1..n) {
        groupCnt[find(head,i)]++
    }

    val tmpGroup: MutableSet<Int> = mutableSetOf()
    val canBetrayerIn: BooleanArray = BooleanArray(n + 1)

    for (betrayerFriend in betrayerFriends) {
        if (tmpGroup.contains(head[betrayerFriend])) {
            canBetrayerIn[head[betrayerFriend]] = false
        } else {
            canBetrayerIn[head[betrayerFriend]] = true
            tmpGroup.add(head[betrayerFriend])
        }
    }

    var ans: Int = 0
    for (i: Int in 1..n) {
        if (groupCnt[i] > 0) {
            if (canBetrayerIn[i]) {
                ans = max(ans, groupCnt[i] + 1)
            } else {
                ans = max(ans, groupCnt[i])
            }
        }
    }
    println(ans)
}

fun union(head: IntArray, p1: Int, p2: Int) {
    val p1Root = find(head, p1)
    val p2Root = find(head, p2)
    if (p1Root < p2Root) {
        head[p2Root] = p1Root
    } else {
        head[p1Root] = p2Root
    }
}

fun find(head: IntArray, p: Int): Int {
    if (head[p] == p) {
        return p
    }
    return find(head, head[p]).also { head[p] = it }
}
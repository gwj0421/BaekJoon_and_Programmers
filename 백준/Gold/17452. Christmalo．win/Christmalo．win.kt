package org.example

import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val n: Int = readLine().toInt()
    val info: Array<MutableMap<Char, WordPosInfo>> = Array(n, { mutableMapOf() })

    for (wordIdx: Int in 0..n - 1) {
        val word: String = readLine()
        for (letterIdx: Int in word.indices) {
            if (info[wordIdx].containsKey(word[letterIdx])) {
                info[wordIdx].get(word[letterIdx])!!.rightDropCnt = word.length - 1 - letterIdx
            } else {
                info[wordIdx].put(word[letterIdx], WordPosInfo(letterIdx, word.length - 1 - letterIdx))
            }
        }
    }

    var ans: Int = Int.MAX_VALUE

    for (middleChar: Char in 'a'..'z') {
        val left: PriorityQueue<IntArray> = PriorityQueue<IntArray>(compareBy { it[1] })
        val right: PriorityQueue<IntArray> = PriorityQueue<IntArray>(compareBy { it[1] })
        for (wordIdx: Int in 0..n - 1) {
            if (info[wordIdx].containsKey(middleChar)) {
                left.add(intArrayOf(wordIdx, info[wordIdx].get(middleChar)!!.leftDropCnt))
                right.add(intArrayOf(wordIdx, info[wordIdx].get(middleChar)!!.rightDropCnt))
            }
        }
        if (left.size < 2 || right.size < 2) {
            continue
        }
        if (left.peek()[0] == right.peek()[0]) {
            val tmpLeft = left.poll()
            val tmpRight = right.poll()
            if (left.peek()[1] < right.peek()[1]) {
                ans = min(ans, left.peek()[1] + tmpRight[1])
            } else {
                ans = min(ans, tmpLeft[1] + right.peek()[1])
            }
        } else {
            ans = min(ans, left.poll()[1] + right.poll()[1])
        }
    }
    if (ans == Int.MAX_VALUE) {
        println(-1)
    } else {
        println(ans)
    }
}


class WordPosInfo(var leftDropCnt: Int, var rightDropCnt: Int) {

}

package org.example

import java.util.*

fun main()
= with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val (n, m) = intArrayOf(12, 6)
    val board = Array(n, { CharArray(m) })
    repeat(n) { idx ->
        board[idx] = readLine().toCharArray()
    }

    Puyo(n, m, board).run()
}


class Puyo(val n: Int, val m: Int, val board: Array<CharArray>) {
    var combo = 0

    companion object {
        val MOVE_PATTERN = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0)
        )
    }

    fun run() {
        while (true) {
            if (!bomb()) {
                break
            }
            combo++
            applyGravity()
        }
        println(combo)
    }

    fun bomb(): Boolean {
        var bombAtLeastOne = false
        val visit = Array(n, { BooleanArray(m) })
        for (i in 0..n - 1) {
            for (j in 0..m - 1) {
                if (!visit[i][j] && board[i][j] != '.') {
                    val needVisit: Queue<IntArray> = LinkedList()
                    needVisit.add(intArrayOf(i, j))

                    visit[i][j] = true
                    if (eachBomb(needVisit, visit, board[i][j])) {
                        bombAtLeastOne = true
                    }
                }
            }
        }
        return bombAtLeastOne
    }

    fun eachBomb(
        needVisit: Queue<IntArray>,
        visit: Array<BooleanArray>,
        targetType: Char
    ): Boolean {
        val footprint: Queue<IntArray> = LinkedList()
        while (needVisit.isNotEmpty()) {
            val (y, x) = needVisit.poll()
            footprint.add(intArrayOf(y, x))
            for (d in 0..3) {
                val ny = y + MOVE_PATTERN[d][0]
                val nx = x + MOVE_PATTERN[d][1]
                if (-1 < ny && ny < n && -1 < nx && nx < m && !visit[ny][nx] && board[ny][nx] == targetType) {
                    visit[ny][nx] = true
                    needVisit.add(intArrayOf(ny, nx))
                }
            }
        }
        if (footprint.size >= 4) {
            while (footprint.isNotEmpty()) {
                val bombTargetCoord = footprint.poll()
                board[bombTargetCoord[0]][bombTargetCoord[1]] = '.'
            }
            return true
        }
        return false
    }

    fun applyGravity() {
        var tmp: Char
        for (j in 0..m - 1) {
            var bottom = n - 1
            for (i in n - 1 downTo 0) {
                if (board[i][j] != '.') {
                    tmp = board[i][j]
                    board[i][j] = '.'
                    board[bottom][j] = tmp
                    bottom--
                }
            }
        }
    }

}

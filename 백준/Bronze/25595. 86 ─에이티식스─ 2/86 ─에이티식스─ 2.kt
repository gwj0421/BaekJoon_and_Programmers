package org.example

fun main() = with(System.`in`.bufferedReader())
{
    val n: Int = readLine().toInt()
    val legionCoord: MutableList<Legion> = mutableListOf()
    var nowzen: Reginleif = Reginleif.default()
    for (y: Int in 0..n - 1) {
        val line: IntArray = readLine().split(" ").map { it.toInt() }.toIntArray()
        for (x: Int in 0..n - 1) {
            if (line[x] == 2) {
                nowzen = Reginleif.getOnReginleit(y, x)
            } else if (line[x] == 1) {
                legionCoord.add(Legion(y, x))
            }
        }
    }

    if (nowzen.annihilate(legionCoord)) {
        println("Lena")
    } else {
        println("Kiriya")
    }
}

/**
 * y홀 x홀, y짝 x짝 => y홀 x짝, y짝 x홀 섬멸 => MODE 1
 * y홀 x짝, y짝 x홀 => y홀 x홀, y짝 x짝 섬멸 => MODE 2
 * */
class Reginleif private constructor(val mode: Int) {
    companion object {
        fun default(): Reginleif {
            return Reginleif(0)
        }

        fun getOnReginleit(y: Int, x: Int): Reginleif {
            if (isEven(y) == isEven(x)) {
                return Reginleif(1)
            }
            return Reginleif(2)
        }
    }

    fun annihilate(legions: MutableList<Legion>): Boolean {
        if (mode == 1) {
            for (legion in legions) {
                if (isEven(legion.y) == isEven(legion.x)) {
                    return false
                }
            }
        } else if (mode == 2) {
            for (legion in legions) {
                if (isEven(legion.y) != isEven(legion.x)) {
                    return false
                }
            }
        }
        return true
    }
}

data class Legion(val y: Int, val x: Int)

private fun isEven(number: Int): Boolean {
    return number % 2 == 0
}

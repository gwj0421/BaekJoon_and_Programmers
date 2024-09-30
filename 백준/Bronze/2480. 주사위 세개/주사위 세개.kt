package org.example

import java.util.*
import kotlin.math.max

fun main() = with(Scanner(System.`in`)) {
    val trial1 = nextInt()
    val trial2 = nextInt()
    val trial3 = nextInt()
    if (trial1 == trial2 && trial2 == trial3) {
        println(10_000 + trial1 * 1_000)
        return
    }
    if (trial1 == trial2 || trial1 == trial3) {
        println(1_000 + trial1 * 100)
        return
    }
    if (trial2 == trial3) {
        println(1_000 + trial2 * 100)
        return
    }
    println(max(trial1, max(trial2, trial3)) * 100)

}
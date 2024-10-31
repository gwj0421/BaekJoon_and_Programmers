package org.example

fun main() = with(System.`in`.bufferedReader())
{
    repeat(readln().toInt()) {
        expect(readln().toLong())
    }
}

fun expect(n: Long) {
    if (n < 2) {
        println("1 0")
    } else {
        println("0 1")
    }
}

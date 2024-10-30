package org.example


fun main() = with(System.`in`.bufferedReader()) {
    repeat(readln().toInt()) {
        val sb = StringBuilder()
        for (s in readln().split(" ")) {
            sb.append(s.reversed()).append(" ")
        }
        println(sb)
    }

}

package org.example

fun main() = with(System.`in`.bufferedReader())
{
    val t = readln().toInt()
    for (index: Int in 1..t) {
        if (isEqualWithoutFormat(readln(), readln())) {
            println("Data Set $index: equal")
        } else {
            println("Data Set $index: not equal")
        }
        println()
    }
}

fun isEqualWithoutFormat(s1: String, s2: String): Boolean {
    return convertFormat(s1).equals(convertFormat(s2))
}

fun convertFormat(s: String) :String{
    return s.uppercase().trim()
        .replace("\\s+".toRegex()," ")
        .replace("[\\;]".toRegex(),",")
        .replace("\\s*([.,;:])\\s*".toRegex(),"$1")
        .replace("\\s*[\\[\\{\\(]\\s*".toRegex(),"(")
        .replace("\\s*[\\]\\}\\)]\\s*".toRegex(),")")
}
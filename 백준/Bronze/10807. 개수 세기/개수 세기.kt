package org.example

import java.io.BufferedReader
import java.io.FileReader

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
= with(System.`in`.bufferedReader())
{
    readLine()
    val numbers = readLine().split(" ")
    val v = readLine()
    println(numbers.count { it.equals(v)})

}
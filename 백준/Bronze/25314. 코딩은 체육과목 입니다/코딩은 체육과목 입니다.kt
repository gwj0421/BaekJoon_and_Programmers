package org.example

import java.io.BufferedReader
import java.io.FileReader

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
= with(System.`in`.bufferedReader())
{
    // 1 2 3 4 = long int
    // 5 6 7 8 = long long int
    println("long ".repeat(readLine().toInt() / 4) + "int")
}
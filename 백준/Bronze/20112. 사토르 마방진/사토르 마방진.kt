package org.example

fun main()
//= with(BufferedReader(FileReader("./src/main/resources/input.txt")))
        = with(System.`in`.bufferedReader())
{
    val n: Int = readLine().toInt()
    val words: Array<CharArray> = Array(n, { CharArray(n) })
    for (i: Int in words.indices) {
        words[i] = readLine().toCharArray()
    }
    for (i: Int in 0..n - 1) {
        for (j: Int in i + 1..n - 1) {
            if (words[i][j] != words[j][i]) {
                println("NO")
                return
            }
        }
    }
    println("YES")

}


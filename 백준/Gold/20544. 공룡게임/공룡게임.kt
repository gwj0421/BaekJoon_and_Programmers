package org.example

fun main()
= with(System.`in`.bufferedReader())
//        = with(BufferedReader(FileReader("./src/main/resources/input.txt")))
{
    val mod: Int = 1_000_000_007
    val n: Int = readLine().toInt()

    if (n < 2) {
        println(0)
        return
    }
    val dp:Array<LongArray> = Array(n+1,{ LongArray(2)})
    dp[0] = longArrayOf(1,0)
    dp[1] = longArrayOf(1,0)
    dp[2] = longArrayOf(2,1)
    for (i: Int in 3..n) {
        dp[i][0] = (
                dp[i-1][0] + dp[i-2][0] + dp[i-3][0]
                ) % mod
        dp[i][1] = (
                dp[i-1][1] +
                        dp[i-2][0] +dp[i-2][1]*2 +
                        dp[i-3][0]*2 + dp[i-3][1]*3
                ) % mod
    }
    println(dp[n][1])
}
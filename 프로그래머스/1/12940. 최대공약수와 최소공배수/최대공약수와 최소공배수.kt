class Solution {
    fun solution(n: Int, m: Int): IntArray = intArrayOf(gcd(n,m),lcm(n,m))

    private tailrec fun gcd(num1: Int, num2: Int): Int {
        var remain:Int = num1 % num2
        return if (remain == 0) {
            num2
        } else {
            gcd(num2,remain)
        }
    }
    private fun lcm(num1: Int, num2: Int) :Int{
        return num1 * num2 / gcd(num1,num2)
    }
}
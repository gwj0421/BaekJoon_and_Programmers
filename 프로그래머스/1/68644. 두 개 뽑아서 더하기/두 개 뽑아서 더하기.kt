class Solution {
    fun solution(numbers: IntArray): IntArray {
        var comb = setOf<Int>()

        for (x in numbers.indices) {
            for (y in x+1..numbers.lastIndex) {
                comb += numbers[x] + numbers[y]
            }
        }
        return comb.sorted().toIntArray()
    }
}
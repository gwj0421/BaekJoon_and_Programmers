class Solution {
    fun solution(s: String): String = s.toCharArray().sortedBy { it }.reversed().joinToString("")
}
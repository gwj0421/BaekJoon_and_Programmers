class Solution {
    fun solution(s: String): Boolean = s.toIntOrNull() != null && (s.length == 4 || s.length == 6)
}
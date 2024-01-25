class Solution {
    fun solution(s: String): String = with(s) {
        substring(length / 2 - 1 + (length % 2)..length / 2)
    }
}

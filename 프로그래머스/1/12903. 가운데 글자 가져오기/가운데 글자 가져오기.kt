class Solution {
    fun solution(s: String): String {
        var answer = ""
        val word = s.split("")
        when {
            word.size % 2 == 0 -> {
                answer = word[word.lastIndex / 2].plus(word[word.lastIndex / 2 + 1])
            }

            else -> answer = word[word.lastIndex / 2]

        }
        return answer
    }
}
class Solution {
    fun solution(s: String): Int {
        val wordMap = mapOf(
            "zero" to "0",
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
        var answer: String = ""
        var word: String = ""
        for (c in s) {
            if (c.isDigit()) {
                answer += c
            } else {
                word += c
                if (word.length > 2 && wordMap.containsKey(word)) {
                    answer += wordMap[word]
                    word = ""
                }
            }

        }
        return answer.toInt()
    }
}
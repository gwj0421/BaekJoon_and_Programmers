class Solution {
    fun solution(letter: String): String {
        var answer: String = ""
        val mos = listOf(
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
        )
        for (word in letter.split(" ")) {
            answer += (mos.indexOf(word)+97).toChar()
        }
        return answer
    }
}
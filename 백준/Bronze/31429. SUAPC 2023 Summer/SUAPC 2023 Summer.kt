package org.example

fun main() = with(System.`in`.bufferedReader()) {
    val (score,penalty) = listOf(
        Team(12, 1600),
        Team(11, 894),
        Team(11, 1327),
        Team(10, 1311),
        Team(9, 1004),
        Team(9, 1178),
        Team(9, 1357),
        Team(8, 837),
        Team(7, 1055),
        Team(6, 556),
        Team(6, 773)
    ).sorted().get(readln().toInt()-1)
    println("$score $penalty")


    close()
}

data class Team(val score: Int, val penalty: Int) : Comparable<Team> {
    override fun compareTo(other: Team): Int {
        val scoreComp = other.score.compareTo(this.score)
        if (scoreComp != 0) {
            return scoreComp
        }
        return this.penalty.compareTo(other.penalty)
    }
}

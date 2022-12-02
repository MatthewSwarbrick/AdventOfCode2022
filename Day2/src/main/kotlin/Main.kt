
fun main() {
    val rounds = getRounds(input)
    part1(rounds)
    part2(rounds)
}

private fun part1(rounds: List<Round>) {
    val totalScore = rounds.sumOf {
        it.player2.score() + it.result().player2.score()
    }
    println("Part 1 | $totalScore")
}

private fun part2(rounds: List<Round>) {
    println("Part 2 | ")
}

private fun getRounds(input: List<String>) = input
    .map {
        val (player1, player2) = it.split(" ")
            .map { letter ->
                when (letter) {
                    "A" -> Hand.ROCK
                    "B" -> Hand.PAPER
                    "C" -> Hand.SCISSORS
                    "X" -> Hand.ROCK
                    "Y" -> Hand.PAPER
                    "Z" -> Hand.SCISSORS
                    else -> throw Error("Unexpected letter from input")
                }
            }

        Round(player1, player2)
    }
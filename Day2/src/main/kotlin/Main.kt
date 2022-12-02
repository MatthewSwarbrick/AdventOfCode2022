
fun main() {
    val part1Rounds = getPart1Rounds(input)
    part1(part1Rounds)

    val part2Rounds = getPart2Rounds(input)
    part2(part2Rounds)
}

private fun part1(rounds: List<Round>) {
    val totalScore = rounds.sumOf {
        it.player2.score() + it.result().player2.score()
    }
    println("Part 1 | $totalScore")
}

private fun part2(rounds: List<Part2Round>) {
    val totalScore = rounds.sumOf {
        it.result.score() + it.toPlayer2Hand().score()
    }
    println("Part 2 | $totalScore")
}

private fun getPart1Rounds(input: List<String>) = input
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

private fun getPart2Rounds(input: List<String>) = input
    .map {
        val (player1, result) = it.split(" ")
            .map { letter ->
                when (letter) {
                    "A" -> Hand.ROCK
                    "B" -> Hand.PAPER
                    "C" -> Hand.SCISSORS
                    "X" -> Result.LOSS
                    "Y" -> Result.DRAW
                    "Z" -> Result.WIN
                    else -> throw Error("Unexpected letter from input")
                }
            }

        Part2Round(player1 as Hand, result as Result)
    }
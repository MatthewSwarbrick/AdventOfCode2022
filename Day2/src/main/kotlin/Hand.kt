enum class Hand {
    ROCK,
    PAPER,
    SCISSORS
}

fun Hand.score() = when(this) {
    Hand.ROCK -> 1
    Hand.PAPER -> 2
    Hand.SCISSORS -> 3
}
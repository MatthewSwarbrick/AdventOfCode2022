data class Part2Round(val player1: Hand, val result: Result) {
    fun toPlayer2Hand() = when(result) {
        Result.WIN -> when(player1) {
            Hand.ROCK -> Hand.PAPER
            Hand.PAPER -> Hand.SCISSORS
            Hand.SCISSORS -> Hand.ROCK
        }
        Result.DRAW -> player1
        Result.LOSS -> when(player1) {
            Hand.ROCK -> Hand.SCISSORS
            Hand.PAPER -> Hand.ROCK
            Hand.SCISSORS -> Hand.PAPER
        }
    }
}